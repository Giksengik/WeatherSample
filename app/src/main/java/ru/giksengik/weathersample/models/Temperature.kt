package ru.giksengik.weathersample.models

import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
@kotlinx.parcelize.Parcelize
data class Temperature(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double ,
    val eve: Double,
    val morn: Double
) : Parcelable
