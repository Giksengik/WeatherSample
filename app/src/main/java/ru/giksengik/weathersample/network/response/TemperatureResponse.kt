package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName

data class TemperatureResponse(
    @SerialName("day") val day: Double,
    @SerialName("min") val min: Double,
    @SerialName("max") val max: Double,
    @SerialName("night") val night: Double,
    @SerialName("eve") val eve: Double,
    @SerialName("morn") val morn: Double
)
