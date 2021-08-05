package ru.giksengik.weathersample.models

import android.os.Parcelable
import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize


@Serializable
@Parcelize
data class FeelsLike (
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
        ) : Parcelable
