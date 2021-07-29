package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName

data class FeelsLikeResponse (
    @SerialName("day") val day: Double,
    @SerialName("night") val night: Double,
    @SerialName("eve") val eve: Double,
    @SerialName("morn") val morn: Double
        )