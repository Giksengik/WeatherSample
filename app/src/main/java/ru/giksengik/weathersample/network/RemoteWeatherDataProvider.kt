package ru.giksengik.weathersample.network

import io.reactivex.Single
import ru.giksengik.weathersample.models.LocationData
import ru.giksengik.weathersample.models.WeatherData

interface RemoteWeatherDataProvider {

    fun getWeather(listOfCoordinates: List<LocationData>) : Single<List<WeatherData>>

    fun getGeoQueryResults(query : String) : Single<List<LocationData>>

}