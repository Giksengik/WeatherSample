package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    @SerialName("name") val name : String,
    @SerialName("lat")val lat : Double,
    @SerialName("lon")val lon : Double,
    @SerialName("country")val country: String
)