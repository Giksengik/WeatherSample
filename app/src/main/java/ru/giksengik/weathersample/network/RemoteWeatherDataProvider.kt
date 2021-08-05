package ru.giksengik.weathersample.network

import io.reactivex.Single
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.request.LocationRequestData

interface RemoteWeatherDataProvider {

    fun getWeather(listOfCoordinates: List<LocationRequestData>) : Single<List<WeatherData>>

    fun getGeoQueryResults(query : String) : Single<List<LocationData>>

}