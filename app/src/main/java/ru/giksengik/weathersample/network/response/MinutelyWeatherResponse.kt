package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName

data class MinutelyWeatherResponse (
    @SerialName("dt") val dt: Long,
    @SerialName("precipitation") val precipitation: Double
)