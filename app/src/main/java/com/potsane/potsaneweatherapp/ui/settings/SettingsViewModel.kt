package com.potsane.potsaneweatherapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.potsane.potsaneweatherapp.ui.base.BaseWeatherAppViewModel

class SettingsViewModel : BaseWeatherAppViewModel() {

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SettingsViewModel() as T
        }
    }
}