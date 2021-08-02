package ru.giksengik.weathersample.models

import kotlinx.serialization.Serializable

@Serializable
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
