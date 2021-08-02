package ru.giksengik.weathersample.models



data class CurrentWeather (
    val dt: Long,
    val sunrise: Double,
    val sunset: Double,
    val temp: Double,
    val feelsLike: Double,
    val clouds: Long,
    val windDeg: Long,
    val weather: List<Weather>,
    val rain: Rain,
    )
