package ru.giksengik.weathersample.db

import io.reactivex.Single
import ru.giksengik.weathersample.models.WeatherData
import javax.inject.Inject

class RoomWeatherSource @Inject constructor(private val dao : WeatherDao) : LocalDataSource {

    override fun saveWeatherData(weatherData: WeatherData) = dao.insert(weatherData)

    override fun getAllWeatherData(): Single<List<WeatherData>> = dao.getAll()

    override fun deleteWeatherData(weatherData: WeatherData) = dao.delete(weatherData)

    override fun updateWeatherData(listOfWeatherData: List<WeatherData>) = dao.updateWeather(listOfWeatherData)

    override fun saveAllWeatherData(listOfWeatherData: List<WeatherData>) = dao.insertAll(listOfWeatherData)

}