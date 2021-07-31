package ru.giksengik.weathersample.models

import kotlinx.serialization.SerialName
import ru.giksengik.weathersample.network.response.FeelsLikeResponse
import ru.giksengik.weathersample.network.response.TemperatureResponse
import ru.giksengik.weathersample.network.response.WeatherResponse

data class DailyWeather (
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    val moonPhase: Double,
    val temp: Temperature,
    val feelsLike: FeelsLike,
    val windDeg: Long,
    val weather: List<Weather>,
    val clouds: Long,
    val rain: Double,
)