package ru.giksengik.weathersample.models

import android.os.Parcelable
import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize

@Serializable
@Parcelize
data class Weather (
    val id: Long,
    val main: String,
    val description: String,
    val iconUrl: String
    ) : Parcelable
