package com.potsane.potsaneweatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.potsane.potsaneweatherapp.ui.base.BaseWeatherAppViewModel

class WeatherViewModel : BaseWeatherAppViewModel() {

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeatherViewModel() as T
        }
    }

}