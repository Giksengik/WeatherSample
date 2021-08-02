package ru.giksengik.weathersample.models

import kotlinx.serialization.Serializable

@Serializable
data class Weather (
    val id: Long,
    val main: String,
    val description: String,
    val iconUrl: String
    )
