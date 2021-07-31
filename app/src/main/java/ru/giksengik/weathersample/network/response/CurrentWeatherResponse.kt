package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponse (
    @SerialName("dt") val dt: Long = 0,
    @SerialName("sunrise") val sunrise: Double = 0.0,
    @SerialName("sunset") val sunset: Double = 0.0,
    @SerialName("temp") val temp: Double = 0.0,
    @SerialName("feels_like") val feelsLike: Double = 0.0,
    @SerialName("pressure") val pressure: Long = 0,
    @SerialName("humidity") val humidity: Long = 0,
    @SerialName("dew_point") val dewPoint: Double = 0.0,
    @SerialName("uvi") val uvi: Double = 0.0,
    @SerialName("clouds") val clouds: Long = 0,
    @SerialName("visibility") val visibility: Long = 0,
    @SerialName("wind_speed") val windSpeed: Double = 0.0,
    @SerialName("wind_deg") val windDeg: Long = 0,
    @SerialName("weather") val weather: List<WeatherResponse> = listOf(),
    @SerialName("rain") val rain: RainResponse = RainResponse(),
    @SerialName("wind_gust") val windGust: Double = 0.0,
    @SerialName("pop") val pop: Double? = 0.0
)