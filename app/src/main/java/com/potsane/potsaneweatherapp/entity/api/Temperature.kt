package com.potsane.potsaneweatherapp.entity.api

import com.google.gson.annotations.SerializedName

data class Temperature(

    @SerializedName("min")
    val min: Double,

    @SerializedName("max")
    val max: Double
)