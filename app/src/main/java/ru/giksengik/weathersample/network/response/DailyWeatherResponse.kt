package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName

data class DailyWeatherResponse (
    @SerialName("dt") val dt: Long,
    @SerialName("sunrise") val sunrise: Long,
    @SerialName("sunset") val sunset: Long,
    @SerialName("moonrise") val moonrise: Long,
    @SerialName("moonset") val moonset: Long,
    @SerialName("moon_phase") val moonPhase: Double,
    @SerialName("temp") val temp: TemperatureResponse,
    @SerialName("feels_like")  val feelsLike: FeelsLikeResponse,
    @SerialName("pressure") val pressure: Long,
    @SerialName("humidity") val humidity: Long,
    @SerialName("dew_point") val dewPoint: Double,
    @SerialName("wind_speed") val windSpeed: Double,
    @SerialName("wind_deg") val windDeg: Long,
    @SerialName("weather") val weather: List<WeatherResponse>,
    @SerialName("clouds") val clouds: Long,
    @SerialName("pop") val pop: Double,
    @SerialName("rain") val rain: Double,
    @SerialName("uvi") val uvi: Double
    )