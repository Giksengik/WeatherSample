package ru.giksengik.weathersample.models


data class Weather (
    val id: Long,
    val main: String,
    val description: String,
    val iconUrl: String
    )
