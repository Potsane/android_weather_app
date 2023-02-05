package com.potsane.potsaneweatherapp.repository

import com.potsane.potsaneweatherapp.BuildConfig
import com.potsane.potsaneweatherapp.common.threading.DispatcherProvider
import com.potsane.potsaneweatherapp.database.WeatherInfoDao
import com.potsane.potsaneweatherapp.database.WeatherInfoEntity
import com.potsane.potsaneweatherapp.entity.api.WeatherResponseItem
import com.potsane.potsaneweatherapp.entity.view.LocationInfo
import com.potsane.potsaneweatherapp.network.WeatherInfoService
import com.potsane.potsaneweatherapp.util.needsToRefresh
import kotlinx.coroutines.withContext

class WeatherInfoRepository(
    private val dispatcherProvider: DispatcherProvider,
    private val weatherInfoService: WeatherInfoService,
    private val weatherInfoDao: WeatherInfoDao
) {

    suspend fun fetchWeatherInfo(locationInfo: LocationInfo): WeatherResponseItem? {
        var weatherInfo: WeatherResponseItem? = null
        withContext(dispatcherProvider.io()) {
            fetchWeatherInfoFromDb(locationInfo)?.let {
                weatherInfo = if (needsToRefresh(it.storageTimestamp)) {
                    fetchWeatherInfoFromService(locationInfo)
                } else {
                    it.weatherResponseItem
                }
            } ?: run {
                weatherInfo = fetchWeatherInfoFromService(locationInfo)
            }
        }
        return weatherInfo
    }

    private suspend fun fetchWeatherInfoFromService(locationInfo: LocationInfo): WeatherResponseItem? {
        val weatherInfo: WeatherResponseItem? = weatherInfoService.getWeatherInfo(
            locationInfo.lat,
            locationInfo.lon,
            BuildConfig.WEATHER_API_KEY
        ).body()
        persistWeatherInfo(weatherInfo, locationInfo)
        return weatherInfo
    }

    private suspend fun fetchWeatherInfoFromDb(locationInfo: LocationInfo): WeatherInfoEntity? {
        return weatherInfoDao.searchLocationWeatherInfo(locationInfo.locationName)
    }

    suspend fun fetchWeatherInfoForAllLocations() = withContext(dispatcherProvider.io()) {
        weatherInfoDao.getWeatherInfoForAllLocations()
    }

    suspend fun deleteWeatherInfoForLocation(locationInfo: LocationInfo): List<WeatherInfoEntity> {
        var updatedWeatherInfoForLocations = listOf<WeatherInfoEntity>()
        withContext(dispatcherProvider.io()) {
            weatherInfoDao.deleteWeatherInfoForLocation(locationInfo.locationName)
            weatherInfoDao.getWeatherInfoForAllLocations()?.let {
                updatedWeatherInfoForLocations = it
            }
        }
        return updatedWeatherInfoForLocations
    }

    private suspend fun persistWeatherInfo(
        weatherInfo: WeatherResponseItem?,
        locationInfo: LocationInfo
    ) {
        weatherInfo?.let {
            val weatherInfoEntity = WeatherInfoEntity(
                locationInfo.locationName,
                it,
                System.currentTimeMillis(),
                locationInfo
            )
            weatherInfoDao.insertWeatherInfo(weatherInfoEntity)
        }
    }
}