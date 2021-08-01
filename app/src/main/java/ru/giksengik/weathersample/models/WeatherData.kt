package ru.giksengik.weathersample.models


data class WeatherData (
    val lat: Double,
    val lon: Double,
    val name : String,
    val region: String,
    val timezone: String,
    val timezoneOffset: Long,
    val currentWeather: CurrentWeather,
    val hourlyWeather : List<CurrentWeather>,
    val dailyWeather: List<DailyWeather>,
    val weatherAlerts : List<WeatherAlert>
    )