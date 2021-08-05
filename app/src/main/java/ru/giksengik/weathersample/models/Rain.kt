package ru.giksengik.weathersample.models

import android.os.Parcelable
import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize

@Serializable
@Parcelize
data class Rain(
    val the1H: Double
) : Parcelable
