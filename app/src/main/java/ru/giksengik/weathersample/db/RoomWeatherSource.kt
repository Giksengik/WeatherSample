package ru.giksengik.weathersample.db

import io.reactivex.Single
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.request.LocationRequestData
import javax.inject.Inject

class RoomWeatherSource @Inject constructor(private val dao : WeatherDao) : LocalDataSource {

    override fun saveWeatherData(weatherData: WeatherData) = dao.insert(weatherData)

    override fun getAllWeatherData(): Single<List<WeatherData>> = dao.getAll()

    override fun getAllWeatherLocations(): Single<List<LocationData>> =
        dao.getAll()
            .map{ weather ->
                weather.map{ data ->
                    LocationData(
                        name = data.name,
                        lon = data.lon,
                        lat = data.lat,
                        country = data.region
                    )
                }
            }

    override fun deleteWeatherData(weatherData: WeatherData) = dao.delete(weatherData)

    override fun updateWeatherData(listOfWeatherData: List<WeatherData>) = dao.updateWeather(listOfWeatherData)

    override fun saveAllWeatherData(listOfWeatherData: List<WeatherData>) = dao.insertAll(listOfWeatherData)

    override fun hasThisWeatherLocation(requestData: LocationRequestData): Boolean =
            dao.hasThisWeather(lat = requestData.lat, lon = requestData.lon)

}