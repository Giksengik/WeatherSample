package ru.giksengik.weathersample.db

import io.reactivex.Flowable
import io.reactivex.Single
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.models.WeatherData

interface LocalDataSource {

    fun saveWeatherData(weatherData: WeatherData)

    fun getAllWeatherData() : Flowable<List<WeatherData>>

    fun getAllWeatherLocations() : Single<List<LocationData>>

    fun getNumOfWeatherLocations() : Single<Int>

    fun deleteWeatherData(weatherData: WeatherData) : Single<Unit>

    fun updateWeatherData(listOfWeatherData: List<WeatherData>)

    fun saveAllWeatherData(listOfWeatherData: List<WeatherData>)

    fun hasThisWeatherLocation(locationData : LocationData) : Single<Boolean>
}
