package ru.giksengik.weathersample.models


data class WeatherData (
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezoneOffset: Long,
    val currentWeather: CurrentWeather,
    val hourlyWeather : List<CurrentWeather>,
    val dailyWeather: DailyWeather,
    val weatherAlerts : List<WeatherAlert>
    )