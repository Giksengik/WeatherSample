package ru.giksengik.weathersample.repositories

import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.request.LocationRequestData
import rx.Observable

interface WeatherRepository {

    fun getWeather(listOfCoordinates: List<LocationRequestData>) : Observable<List<WeatherData>>

    fun saveWeather(weatherData: WeatherData)

    fun updateWeather(listOfWeatherData: List<WeatherData>)

    fun getWeatherDataFromLocalDataSource() : Observable<List<WeatherData>>
}