package com.potsane.potsaneweatherapp.entity.api

import com.google.gson.annotations.SerializedName

data class WeatherResponseItem(

    @SerializedName("current")
    val currentWeatherInfo: CurrentWeatherInfo,

    @SerializedName("daily")
    val daily : List<DailyWeatherInfo>
)