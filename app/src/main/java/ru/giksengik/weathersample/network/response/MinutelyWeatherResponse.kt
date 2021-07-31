package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MinutelyWeatherResponse (
    @SerialName("dt") val dt: Long = 0,
    @SerialName("precipitation") val precipitation: Double = 0.0
)