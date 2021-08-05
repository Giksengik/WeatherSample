package ru.giksengik.weathersample.repositories


import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import ru.giksengik.weathersample.db.LocalDataSource
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.RemoteWeatherDataProvider
import ru.giksengik.weathersample.network.request.LocationRequestData
import ru.giksengik.weathersample.ui.weatheradd.AddWeatherViewState
import ru.giksengik.weathersample.ui.weatherlist.WeatherListViewState
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteWeatherDataProvider: RemoteWeatherDataProvider,
    private val localDataSource: LocalDataSource)
    : WeatherRepository{

    override fun loadAllWeather(): Observable<WeatherListViewState> =
        Observable.create { emitter ->
            localDataSource.getAllWeatherLocations()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnSuccess{ locations ->
                    if(locations.isNotEmpty()) {
                        remoteWeatherDataProvider
                                .getWeather(locations.map { location ->
                                    LocationRequestData(
                                            lon = location.lon,
                                            lat = location.lat,
                                            name = location.name,
                                            region = location.country
                                    )
                                })
                                .observeOn(Schedulers.io())
                                .subscribeOn(Schedulers.io())
                                .doOnSuccess { weatherData ->
                                    updateWeather(weatherData)
                                }
                                .doOnError { throwable ->
                                    emitter.onError(throwable)
                                }
                                .subscribe()
                    }
                    emitter.onNext(WeatherListViewState.Success.Loaded(listOf()))
                }
                .doOnError{
                    emitter.onError(it)
                }
                .subscribe()

        }

    override fun getAllWeather(): Flowable<List<WeatherData>> =
            localDataSource.getAllWeatherData()


    override fun addWeatherLocation(locationData: LocationData): Single<AddWeatherViewState> =
        Single.create { emitter : SingleEmitter<AddWeatherViewState> ->
            val request = LocationRequestData(
                lon = locationData.lon,
                lat = locationData.lat,
                name = locationData.name,
                region = locationData.country
            )
            if(!isWeatherLocationWritten(locationRequestData = request)){
                getWeather(request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .doOnSuccess{ data ->
                        saveWeather(weatherData = data)
                        emitter.onSuccess(AddWeatherViewState.WeatherPlaceAdded)
                    }
                    .doOnError{
                        emitter.onError(it)
                    }
                    .subscribe()
            }
            else emitter.onSuccess(AddWeatherViewState.Error.PlaceAlreadyWritten)
        }

    override fun deleteWeather(weatherData: WeatherData) {
        localDataSource.deleteWeatherData(weatherData)
    }


    override fun getWeather(locationRequestData: LocationRequestData) : Single<WeatherData> =
        remoteWeatherDataProvider.getWeather(listOf(locationRequestData))
            .map {
                it[0]
            }


    private fun saveWeather(weatherData: WeatherData) =
        localDataSource.saveWeatherData(weatherData)

    private fun saveAllWeather(listOfWeatherData: List<WeatherData>) =
        localDataSource.saveAllWeatherData(listOfWeatherData)

    private fun updateWeather(listOfWeatherData: List<WeatherData>) =
        localDataSource.updateWeatherData(listOfWeatherData)


    private fun isWeatherLocationWritten(locationRequestData: LocationRequestData) : Boolean =
            localDataSource.hasThisWeatherLocation(locationRequestData)

    override fun getLocationsByQuery(query: String): Single<List<LocationData>> =
            remoteWeatherDataProvider.getGeoQueryResults(query)




}