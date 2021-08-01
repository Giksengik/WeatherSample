package ru.giksengik.weathersample.network.request

data class LocationRequestData (
    val lon : Double,
    val lat : Double,
    val name : String,
    val region : String
    )