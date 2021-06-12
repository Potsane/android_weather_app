package com.potsane.potsaneweatherapp.entity.api

import com.google.gson.annotations.SerializedName

data class CurrentWeatherInfo(

    @SerializedName("dt")
    val date: Long,

    @SerializedName("temp")
    val temp: Double,

    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("uvi")
    val uvi: Int,

    @SerializedName("wind_speed")
    val windSpeed: Double,

    @SerializedName("weather")
    val weather : Weather
)