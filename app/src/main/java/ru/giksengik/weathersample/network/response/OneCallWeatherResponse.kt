package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OneCallWeatherResponse(
    @SerialName("lat") val lat: Double,
    @SerialName("lon") val lon: Double,
    @SerialName("timezone") val timezone: String,
    @SerialName("timezone_offset") val timezoneOffset: Long,
    @SerialName("current") val current: CurrentWeatherResponse = CurrentWeatherResponse(),
    @SerialName("minutely") val minutely: List<MinutelyWeatherResponse> = listOf(),
    @SerialName("hourly") val hourly: List<CurrentWeatherResponse> = listOf(),
    @SerialName("daily") val daily: List<DailyWeatherResponse> = listOf(),
    @SerialName("alerts") val alerts: List<WeatherAlertResponse> = listOf()
)