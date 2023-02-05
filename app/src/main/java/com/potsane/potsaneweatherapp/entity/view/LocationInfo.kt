package com.potsane.potsaneweatherapp.entity.view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class LocationInfo(
    var locationName: String,
    var lat: Double,
    var lon: Double
) : Parcelable