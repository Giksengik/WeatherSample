package ru.giksengik.weathersample.repositories

import ru.giksengik.weathersample.models.WeatherData
import ru.giksengik.weathersample.network.RemoteWeatherDataProvider
import ru.giksengik.weathersample.network.request.Coordinates
import rx.Observable
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteWeatherDataProvider: RemoteWeatherDataProvider)
    : WeatherRepository{

    override fun getWeather(listOfCoordinates: List<Coordinates>): Observable<List<WeatherData>> =
            remoteWeatherDataProvider.getWeather(listOfCoordinates)


}