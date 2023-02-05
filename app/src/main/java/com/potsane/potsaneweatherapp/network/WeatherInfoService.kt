package com.potsane.potsaneweatherapp.network

import com.potsane.potsaneweatherapp.entity.api.WeatherResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInfoService {

    @GET("onecall")
    suspend fun getWeatherInfo(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<WeatherResponseItem>
}