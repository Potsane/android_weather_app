package com.potsane.potsaneweatherapp.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.potsane.potsaneweatherapp.ui.base.BaseWeatherAppViewModel

class LocationsViewModel : BaseWeatherAppViewModel() {

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LocationsViewModel() as T
        }
    }
}