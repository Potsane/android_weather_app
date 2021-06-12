package com.potsane.potsaneweatherapp.util

import com.potsane.potsaneweatherapp.entity.api.CurrentWeatherInfo
import com.potsane.potsaneweatherapp.entity.view.ExtraWeatherDetail

fun extractExtraWeatherDetails(currentWeatherInfo: CurrentWeatherInfo): List<ExtraWeatherDetail> {
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
            "${currentWeatherInfo.uvi} - ${uvIndexLabel(currentWeatherInfo.uvi)}",
            "Uv index"
        )
    )
}

fun uvIndexLabel(value: Double): String {
    return if (value <= 2) {
        "Low"
    } else if (value in 3.0..5.0) {
        "Moderate"
    } else if (value.equals(6) || value.equals(7)) {
        "High"
    } else if (value in 8.0..10.0) {
        "Very high"
    } else {
        "Extreme"
    }
}