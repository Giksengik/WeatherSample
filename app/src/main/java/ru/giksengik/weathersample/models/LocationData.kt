package ru.giksengik.weathersample.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationData(
    val name : String,
    val lat : Double,
    val lon : Double,
    val country: String
) : Parcelable
