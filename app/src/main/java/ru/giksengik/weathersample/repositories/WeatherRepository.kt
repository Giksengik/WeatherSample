package ru.giksengik.weathersample.repositories

import io.reactivex.Observable
import io.reactivex.Single
import ru.giksengik.weathersample.models.GeoQueryResults
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.request.LocationRequestData
import ru.giksengik.weathersample.ui.weatheradd.AddWeatherViewState

interface WeatherRepository {

    fun getAllWeather() : Observable<List<WeatherData>>

    fun getWeather(locationRequestData: LocationRequestData) : Single<WeatherData>

    fun getLocationsByQuery(query : String) : Single<List<LocationData>>

    fun addWeatherLocation(locationData: LocationData) : Single<AddWeatherViewState>
}