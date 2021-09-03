package ru.giksengik.weathersample.repositories

import io.reactivex.Flowable
import io.reactivex.Single
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.models.WeatherData

interface WeatherRepository {

    fun loadAllWeather() : Single<List<WeatherData>>

    fun getAllWeather() : Flowable<List<WeatherData>>

    fun getWeather(locationData : LocationData) : Single<WeatherData>

    fun getLocationsByQuery(query : String) : Single<List<LocationData>>

    fun addWeatherLocation(locationData: LocationData) : Single<WeatherData>

    fun deleteWeather(weatherData: WeatherData) : Single<Unit>
}
