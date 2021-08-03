package ru.giksengik.weathersample.db

import io.reactivex.Single
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.request.LocationRequestData

interface LocalDataSource {

    fun saveWeatherData(weatherData: WeatherData)

    fun getAllWeatherData() : Single<List<WeatherData>>

    fun getAllWeatherLocations() : Single<List<LocationData>>

    fun deleteWeatherData(weatherData: WeatherData)

    fun updateWeatherData(listOfWeatherData: List<WeatherData>)

    fun saveAllWeatherData(listOfWeatherData: List<WeatherData>)

    fun hasThisWeatherLocation(requestData: LocationRequestData) : Boolean
}
