package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWeatherResponse (
    @SerialName("dt") val dt: Long = 0,
    @SerialName("sunrise") val sunrise: Long = 0,
    @SerialName("sunset") val sunset: Long = 0,
    @SerialName("moonrise") val moonrise: Long = 0,
    @SerialName("moonset") val moonset: Long = 0,
    @SerialName("moon_phase") val moonPhase: Double = 0.0,
    @SerialName("temp") val temp: TemperatureResponse = TemperatureResponse(),
    @SerialName("feels_like")  val feelsLike: FeelsLikeResponse = FeelsLikeResponse(),
    @SerialName("pressure") val pressure: Long = 0,
    @SerialName("humidity") val humidity: Long = 0,
    @SerialName("dew_point") val dewPoint: Double = 0.0,
    @SerialName("wind_speed") val windSpeed: Double = 0.0,
    @SerialName("wind_deg") val windDeg: Long = 0,
    @SerialName("weather") val weather: List<WeatherResponse> = listOf(),
    @SerialName("clouds") val clouds: Long = 0,
    @SerialName("pop") val pop: Double = 0.0,
    @SerialName("rain") val rain: Double = 0.0,
    @SerialName("uvi") val uvi: Double = 0.0
    )