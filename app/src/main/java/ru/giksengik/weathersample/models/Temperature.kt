package ru.giksengik.weathersample.models

import kotlinx.serialization.SerialName

data class Temperature(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double ,
    val eve: Double,
    val morn: Double
)
