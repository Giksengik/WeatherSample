package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName

data class CurrentWeatherResponse (
    @SerialName("dt") val dt: Long,
    @SerialName("sunrise") val sunrise: Long? = null,
    @SerialName("sunset") val sunset: Long? = null,
    @SerialName("temp") val temp: Double,
    @SerialName("feels_like") val feelsLike: Double,
    @SerialName("pressure") val pressure: Long,
    @SerialName("humidity") val humidity: Long,
    @SerialName("dew_point") val dewPoint: Double,
    @SerialName("uvi") val uvi: Double,
    @SerialName("clouds") val clouds: Long,
    @SerialName("visibility") val visibility: Long,
    @SerialName("wind_speed") val windSpeed: Double,
    @SerialName("wind_deg") val windDeg: Long,
    @SerialName("weather") val weather: List<WeatherResponse>,
    @SerialName("rain") val rain: RainResponse? = null,
    @SerialName("wind_gust") val windGust: Double? = null,
    @SerialName("pop") val pop: Long? = null
)