package com.potsane.potsaneweatherapp.repository

import android.location.Address
import com.potsane.potsaneweatherapp.BuildConfig
import com.potsane.potsaneweatherapp.common.threading.DispatcherProvider
import com.potsane.potsaneweatherapp.network.WeatherInfoService
import kotlinx.coroutines.withContext

class WeatherInfoRepository(
    private val dispatcherProvider: DispatcherProvider,
    private val weatherInfoService: WeatherInfoService
) {

    suspend fun maybeFetchWeatherInfo(address: Address) = withContext(dispatcherProvider.io()) {
        weatherInfoService.getWeatherInfo(
            address.latitude,
            address.longitude,
            BuildConfig.WEATHER_API_KEY
        )
    }
}