package ru.giksengik.weathersample.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoQueryResponse (
   val locations : List<LocationResponse> = listOf()
)