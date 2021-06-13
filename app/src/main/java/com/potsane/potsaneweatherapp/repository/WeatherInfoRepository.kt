package com.potsane.potsaneweatherapp.repository

import android.location.Address
import com.potsane.potsaneweatherapp.BuildConfig
import com.potsane.potsaneweatherapp.common.threading.DispatcherProvider
import com.potsane.potsaneweatherapp.database.WeatherInfoDao
import com.potsane.potsaneweatherapp.database.WeatherInfoEntity
import com.potsane.potsaneweatherapp.entity.api.WeatherResponseItem
import com.potsane.potsaneweatherapp.network.WeatherInfoService
import com.potsane.potsaneweatherapp.util.needsToRefresh
import kotlinx.coroutines.withContext

class WeatherInfoRepository(
    private val dispatcherProvider: DispatcherProvider,
    private val weatherInfoService: WeatherInfoService,
    private val weatherInfoDao: WeatherInfoDao
) {

    suspend fun fetchWeatherInfo(address: Address): WeatherResponseItem? {
        var weatherInfo: WeatherResponseItem? = null
        withContext(dispatcherProvider.io()) {
            fetchWeatherInfoFromDb(address)?.let {
                weatherInfo = if (needsToRefresh(it.storageTimestamp)) {
                    fetchWeatherInfoFromService(address)
                } else {
                    it.weatherResponseItem
                }
            } ?: run {
                weatherInfo = fetchWeatherInfoFromService(address)
            }
        }
        return weatherInfo
    }

    private suspend fun fetchWeatherInfoFromService(address: Address): WeatherResponseItem? {
        val weatherInfo: WeatherResponseItem? = weatherInfoService.getWeatherInfo(
            address.latitude,
            address.longitude,
            BuildConfig.WEATHER_API_KEY
        ).body()
        persistWeatherInfo(weatherInfo, address)
        return weatherInfo
    }

    private suspend fun fetchWeatherInfoFromDb(address: Address): WeatherInfoEntity? {
        return weatherInfoDao.searchLocationWeatherInfo(address.locality)
    }

    private suspend fun persistWeatherInfo(
        weatherInfo: WeatherResponseItem?,
        address: Address
    ) {
        weatherInfo?.let {
            val weatherInfoEntity = WeatherInfoEntity(
                address.locality,
                it,
                System.currentTimeMillis()
            )
            weatherInfoDao.insertWeatherInfo(weatherInfoEntity)
        }
    }
}