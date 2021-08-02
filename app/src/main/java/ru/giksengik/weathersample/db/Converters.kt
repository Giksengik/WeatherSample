package ru.giksengik.weathersample.db

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import ru.giksengik.weathersample.models.CurrentWeather
import ru.giksengik.weathersample.models.DailyWeather
import ru.giksengik.weathersample.models.WeatherAlert

class Converters {

    private val json by lazy {
        Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }

    @TypeConverter
    fun currentWeatherToJson(currentWeather: CurrentWeather) : String =
        json.encodeToString(currentWeather)

    @TypeConverter
    fun fromJsonToCurrentWeather(string: String) : CurrentWeather =
        json.decodeFromString(string)

    @TypeConverter
    fun dailyWeatherToJson(dailyWeather: DailyWeather) : String =
        json.encodeToString(dailyWeather)

    @TypeConverter
    fun fromJsonToDailyWeather(string : String) : DailyWeather =
        json.decodeFromString(string)

    @TypeConverter
    fun fromWeatherAlertToJson(weatherAlert: WeatherAlert) : String =
        json.encodeToString(weatherAlert)

    @TypeConverter
    fun fromJsonToWeatherAlert(string : String) : WeatherAlert =
        json.decodeFromString(string)

}