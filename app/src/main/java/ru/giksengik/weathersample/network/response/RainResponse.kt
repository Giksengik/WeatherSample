package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RainResponse (
    @SerialName("1h") val the1H: Double = 0.0
)