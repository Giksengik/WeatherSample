package ru.giksengik.weathersample.network

import ru.giksengik.weathersample.models.GeoQueryResults
import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.request.LocationRequestData
import rx.Observable

interface RemoteWeatherDataProvider {

    fun getWeather(listOfCoordinates: List<LocationRequestData>) : Observable<List<WeatherData>>

    fun getGeoQueryResults(query : String) : Observable<GeoQueryResults>

}