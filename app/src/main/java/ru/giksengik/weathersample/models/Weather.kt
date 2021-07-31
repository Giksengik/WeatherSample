package ru.giksengik.weathersample.models

import kotlinx.serialization.SerialName

data class Weather (
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
        )