package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeelsLikeResponse (
    @SerialName("day") val day: Double = 0.0,
    @SerialName("night") val night: Double = 0.0,
    @SerialName("eve") val eve: Double = 0.0,
    @SerialName("morn") val morn: Double = 0.0
        )