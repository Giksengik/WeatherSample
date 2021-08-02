package ru.giksengik.weathersample.models

import androidx.room.Embedded
import androidx.room.Entity


@Entity( primaryKeys = ["lat", "lon"])
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
