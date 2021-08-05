package ru.giksengik.weathersample.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity( primaryKeys = ["lat", "lon"])
@Parcelize
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
    ) : Parcelable
