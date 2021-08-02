package ru.giksengik.weathersample.db

import ru.giksengik.weathersample.models.WeatherData
import rx.Observable

interface LocalDataSource {

    fun saveWeatherData(weatherData: WeatherData)

    fun getAllWeatherData() : Observable<List<WeatherData>>

    fun deleteWeatherData(weatherData: WeatherData)

    fun updateWeatherData(listOfWeatherData: List<WeatherData>)
}
