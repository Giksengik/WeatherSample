package ru.giksengik.weathersample.repositories

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ru.giksengik.weathersample.models.GeoQueryResults
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.request.LocationRequestData
import ru.giksengik.weathersample.ui.weatheradd.AddWeatherViewState
import ru.giksengik.weathersample.ui.weatherlist.WeatherListViewState

interface WeatherRepository {

    fun loadAllWeather() : Observable<WeatherListViewState>

    fun getAllWeather() : Flowable<List<WeatherData>>

    fun getWeather(locationRequestData: LocationRequestData) : Single<WeatherData>

    fun getLocationsByQuery(query : String) : Single<List<LocationData>>

    fun addWeatherLocation(locationData: LocationData) : Single<AddWeatherViewState>

    fun deleteWeather(weatherData: WeatherData)
}