package ru.giksengik.weathersample.db

import io.reactivex.Single
import ru.giksengik.weathersample.models.WeatherData

interface LocalDataSource {

    fun saveWeatherData(weatherData: WeatherData)

    fun getAllWeatherData() : Single<List<WeatherData>>

    fun deleteWeatherData(weatherData: WeatherData)

    fun updateWeatherData(listOfWeatherData: List<WeatherData>)

    fun saveAllWeatherData(listOfWeatherData: List<WeatherData>)
}
