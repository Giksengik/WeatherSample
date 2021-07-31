package ru.giksengik.weathersample.repositories

import ru.giksengik.weathersample.network.request.Coordinates

interface WeatherRepository {
    fun getWeather(coordinates: Coordinates)
}