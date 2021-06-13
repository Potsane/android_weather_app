package com.potsane.potsaneweatherapp.util

import com.potsane.potsaneweatherapp.entity.api.CurrentWeatherInfo
import com.potsane.potsaneweatherapp.entity.view.ExtraWeatherDetail

fun extractExtraWeatherDetails(currentWeatherInfo: CurrentWeatherInfo?): List<ExtraWeatherDetail> {
    currentWeatherInfo?.let {
        return listOf(
            ExtraWeatherDetail(
                "${currentWeatherInfo.windSpeed} miles/h",
                "Wind speed"
            ),
            ExtraWeatherDetail(
                "${currentWeatherInfo.humidity} %",
                "Humidity"
            ),
            ExtraWeatherDetail(
                "${currentWeatherInfo.uvi}",
//            "${currentWeatherInfo.uvi} - ${uvIndexLabel(currentWeatherInfo.uvi)}",
                "Uv index"
            )
        )
    } ?: run { return emptyList() }
}

fun uvIndexLabel(value: Double): String {
    return if (value <= 2.0) {
        "Low"
    } else if (value > 2.0 && value <= 5.0) {
        "Moderate"
    } else if (value > 5.0 && value <= 8.0) {
        "High"
    } else if (value > 8.0 && value <= 10.0) {
        "Very high"
    } else {
        "Extreme"
    }
}