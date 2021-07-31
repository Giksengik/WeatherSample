package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherAlertResponse (
    @SerialName("sender_name") val senderName: String = "",
    @SerialName("event") val event: String = "",
    @SerialName("start") val start: Long = 0,
    @SerialName("end") val end: Long = 0,
    @SerialName("description") val description: String = "",
    @SerialName("tags") val tags: List<String> = listOf()
    )