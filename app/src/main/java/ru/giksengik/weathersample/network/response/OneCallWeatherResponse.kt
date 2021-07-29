package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName

data class OneCallWeatherResponse(
    @SerialName("lat") val lat: Double,
    @SerialName("lon") val lon: Double,
    @SerialName("timezone") val timezone: String,
    @SerialName("timezone_offset") val timezoneOffset: Long,
    @SerialName("current") val current: CurrentWeatherResponse,
    @SerialName("minutely") val minutely: List<MinutelyWeatherResponse>,
    @SerialName("hourly") val hourly: List<CurrentWeatherResponse>,
    @SerialName("daily") val daily: List<DailyWeatherResponse>,
    @SerialName("alerts") val alerts: List<WeatherAlertResponse>
)