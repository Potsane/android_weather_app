package com.potsane.potsaneweatherapp.entity.api

import com.google.gson.annotations.SerializedName

data class DailyWeatherInfo(

    @SerializedName("temp")
    val temp: Temperature,

    @SerializedName("dt")
    val date: Long,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("uvi")
    val uvi: Int,

    @SerializedName("wind_speed")
    val windSpeed: Double,

    @SerializedName("weather")
    val weather: Weather
)