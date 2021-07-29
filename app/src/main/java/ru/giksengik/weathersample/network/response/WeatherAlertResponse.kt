package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName

data class WeatherAlertResponse (
    @SerialName("sender_name") val senderName: String,
    @SerialName("event") val event: String,
    @SerialName("start") val start: Long,
    @SerialName("end") val end: Long,
    @SerialName("description") val description: String,
    @SerialName("tags") val tags: List<String>
    )