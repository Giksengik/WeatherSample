package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName

data class WeatherResponse (
    @SerialName("id") val id: Long,
    @SerialName("main") val main: String,
    @SerialName("description") val description: String,
    @SerialName("icon") val icon: String
)