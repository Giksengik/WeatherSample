package ru.giksengik.weathersample.repositories


import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import ru.giksengik.weathersample.db.LocalDataSource
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.RemoteWeatherDataProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteWeatherDataProvider: RemoteWeatherDataProvider,
    private val localDataSource: LocalDataSource
) : WeatherRepository {

    private fun getAllWeatherLocations() =
        localDataSource.getAllWeatherLocations()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())

    override fun loadAllWeather(): Single<List<WeatherData>> =
        getAllWeatherLocations()
            .flatMap { locations ->
                getAllWeatherByLocations(locations)
            }
            .doOnSuccess { weatherData ->
                saveAllWeather(weatherData)
            }


    private fun getAllWeatherByLocations(locations: List<LocationData>) =
        remoteWeatherDataProvider.getWeather(locations)

    override fun getAllWeather(): Flowable<List<WeatherData>> =
        localDataSource.getAllWeatherData()

    override fun addWeatherLocation(locationData: LocationData): Single<WeatherData> =
        isWeatherLocationWritten(locationData)
            .flatMap { isWritten ->
                if(isWritten)
                    Single.error(IllegalArgumentException("This location is already exist"))
                else
                    getWeather(locationData)
            }
            .doOnSuccess{ weatherData ->
                saveWeather(weatherData)
            }


    override fun deleteWeather(weatherData: WeatherData)  =
            localDataSource.deleteWeatherData(weatherData)



    override fun getWeather(locationData: LocationData): Single<WeatherData> =
        remoteWeatherDataProvider.getWeather(listOf(locationData))
            .map { it[0] }

    private fun saveWeather(weatherData: WeatherData) =
        localDataSource.saveWeatherData(weatherData)

    private fun saveAllWeather(listOfWeatherData: List<WeatherData>) =
        localDataSource.saveAllWeatherData(listOfWeatherData)

    private fun updateWeather(listOfWeatherData: List<WeatherData>) =
        localDataSource.updateWeatherData(listOfWeatherData)

    private fun isWeatherLocationWritten(locationData: LocationData): Single<Boolean> =
        localDataSource.hasThisWeatherLocation(locationData)

    override fun getLocationsByQuery(query: String): Single<List<LocationData>> =
        remoteWeatherDataProvider.getGeoQueryResults(query)

}