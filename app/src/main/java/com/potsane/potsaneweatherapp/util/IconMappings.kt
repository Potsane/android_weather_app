package com.potsane.potsaneweatherapp.util

import com.potsane.potsaneweatherapp.R

fun getConditionIcon(iconKey: String): Int {
    return when (iconKey) {
        "01d" -> R.drawable.ic_condition_sunny
        "02d" -> R.drawable.ic_condition_clouds_sunny
        "03d", "03n", "04d", "04n" -> R.drawable.ic_condition_cloudy
        "09d", "10d" -> R.drawable.ic_condition_rain
        "11d", "11n" -> R.drawable.ic_condition_storms
        "13d", "13n" -> R.drawable.ic_condition_snow
        "01n" -> R.drawable.ic_condition_clear_night
        "02n" -> R.drawable.ic_condition_cloudy_night
        "09n", "10n" -> R.drawable.ic_condition_rainy_night
        "50d", "50n" -> R.drawable.ic_condition_windy
        //TODO - get default image
        else -> R.drawable.ic_condition_sunny
    }
}