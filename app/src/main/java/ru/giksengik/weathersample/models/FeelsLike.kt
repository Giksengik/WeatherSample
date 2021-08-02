package ru.giksengik.weathersample.models

import kotlinx.serialization.Serializable

@Serializable
data class FeelsLike (
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
        )
