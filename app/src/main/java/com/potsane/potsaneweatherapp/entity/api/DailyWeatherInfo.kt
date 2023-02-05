package com.potsane.potsaneweatherapp.entity.api

import com.google.gson.annotations.SerializedName

data class DailyWeatherInfo(

    @SerializedName("temp")
    val temp: Temperature,

    @SerializedName("dt")
    val date: Long,

    @SerializedName("humidity")
    val humidity: Double,

    @SerializedName("uvi")
    val uvi: Double,

    @SerializedName("wind_speed")
    val windSpeed: Double,

    @SerializedName("weather")
    val weather: List<Weather>
)