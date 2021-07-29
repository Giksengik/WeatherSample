package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName

data class RainResponse (
    @SerialName("1h") val the1H: Double
)