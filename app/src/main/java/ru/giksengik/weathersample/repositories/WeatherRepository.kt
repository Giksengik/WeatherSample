package ru.giksengik.weathersample.repositories

import io.reactivex.Single
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.request.LocationRequestData

interface WeatherRepository {

    fun getWeather(listOfCoordinates: List<LocationRequestData>) : Single<List<WeatherData>>

    fun saveWeather(weatherData: WeatherData)

    fun saveAllWeather(listOfWeatherData: List<WeatherData>)

    fun updateWeather(listOfWeatherData: List<WeatherData>)

    fun getWeatherDataFromLocalDataSource() : Single<List<WeatherData>>
}