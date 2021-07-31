package ru.giksengik.weathersample.models

import kotlinx.serialization.SerialName

data class FeelsLike (
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
        )