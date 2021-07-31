package ru.giksengik.weathersample.repositories

import ru.giksengik.weathersample.network.RemoteWeatherDataProvider
import ru.giksengik.weathersample.network.request.Coordinates
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor( val remoteWeatherDataProvider: RemoteWeatherDataProvider) : WeatherRepository{
    override fun getWeather(coordinates: Coordinates) {
        remoteWeatherDataProvider.getWeather(coordinates)
    }

}