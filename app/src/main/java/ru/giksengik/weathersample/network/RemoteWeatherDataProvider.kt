package ru.giksengik.weathersample.network

import ru.giksengik.weathersample.network.request.Coordinates

interface RemoteWeatherDataProvider {
    fun getWeather(coordinates: Coordinates)
}