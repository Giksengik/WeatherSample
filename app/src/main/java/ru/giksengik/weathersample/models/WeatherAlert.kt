package ru.giksengik.weathersample.models

import kotlinx.serialization.SerialName

data class WeatherAlert (
    val senderName: String,
    val event: String,
    val start: Long,
    val end: Long,
    val description: String,
    val tags: List<String>
    )