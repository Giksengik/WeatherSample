package ru.giksengik.weathersample.db

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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
    fun listOfCurrentWeatherToJson(listOfCurrentWeather: List<CurrentWeather>) : String =
        json.encodeToString(listOfCurrentWeather)

    @TypeConverter
    fun fromJsonToListOfCurrentWeather(string: String) : List<CurrentWeather> =
        json.decodeFromString(string)

    @TypeConverter
    fun listOfDailyWeatherToJson(listOfDailyWeather: List<DailyWeather>) : String =
        json.encodeToString(listOfDailyWeather)

    @TypeConverter
    fun fromJsonToListOfDailyWeather(string : String) : List<DailyWeather> =
        json.decodeFromString(string)

    @TypeConverter
    fun fromListOfWeatherAlertToJson(listOfWeatherAlert: List<WeatherAlert>) : String =
        json.encodeToString(listOfWeatherAlert)

    @TypeConverter
    fun fromJsonToListOfWeatherAlert(string : String) : List<WeatherAlert> =
        json.decodeFromString(string)

}