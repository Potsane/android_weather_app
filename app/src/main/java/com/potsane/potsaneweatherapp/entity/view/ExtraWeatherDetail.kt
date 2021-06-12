package com.potsane.potsaneweatherapp.entity.view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExtraWeatherDetail(
    val value : String,
    val label : String
) : Parcelable