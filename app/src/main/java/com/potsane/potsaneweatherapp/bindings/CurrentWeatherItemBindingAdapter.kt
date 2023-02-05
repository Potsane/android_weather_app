package com.potsane.potsaneweatherapp.bindings

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.potsane.potsaneweatherapp.entity.api.CurrentWeatherInfo
import com.potsane.potsaneweatherapp.entity.api.Weather

@BindingAdapter("weatherDescription")
fun getWeatherDescription(textView: TextView, currentWeatherInfo: CurrentWeatherInfo?) {
    val currentWeatherItem = currentWeatherInfo?.weather?.firstOrNull()
    currentWeatherItem?.let { textView.text = it.main } ?: run { textView.text = "" }
}