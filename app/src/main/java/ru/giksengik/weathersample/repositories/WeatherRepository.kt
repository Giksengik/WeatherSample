package ru.giksengik.weathersample.repositories

import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.request.Coordinates
import rx.Observable

interface WeatherRepository {

    fun getWeather(listOfCoordinates: List<Coordinates>) : Observable<List<WeatherData>>

}