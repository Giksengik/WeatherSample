package ru.giksengik.weathersample.db

import io.reactivex.Flowable
import io.reactivex.Single
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.models.WeatherData
import javax.inject.Inject

class RoomWeatherSource @Inject constructor(private val dao : WeatherDao) : LocalDataSource {

    override fun saveWeatherData(weatherData: WeatherData) = dao.insert(weatherData)

    override fun getAllWeatherData(): Flowable<List<WeatherData>> = dao.getAll()

    override fun getAllWeatherLocations(): Single<List<LocationData>> =
        dao.getAll()
                .firstOrError()
                .map{ weather ->
                weather.map{ data ->
                    LocationData(
                        name = data.name,
                        lon = data.lon,
                        lat = data.lat,
                        country = data.region
                    ) }
            }

    override fun getNumOfWeatherLocations(): Single<Int> = dao.getNumOfLocations()

    override fun deleteWeatherData(weatherData: WeatherData) =
        Single.fromCallable{dao.delete(weatherData)}

    override fun updateWeatherData(listOfWeatherData: List<WeatherData>) = dao.updateWeather(listOfWeatherData)

    override fun saveAllWeatherData(listOfWeatherData: List<WeatherData>) = dao.insertAll(listOfWeatherData)

    override fun hasThisWeatherLocation(locationData: LocationData): Single<Boolean> =
            dao.hasThisWeather(lat = locationData.lat, lon = locationData.lon)

}