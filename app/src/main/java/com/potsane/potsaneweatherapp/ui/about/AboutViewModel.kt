package com.potsane.potsaneweatherapp.ui.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.potsane.potsaneweatherapp.ui.base.BaseWeatherAppViewModel

class AboutViewModel : BaseWeatherAppViewModel() {

    fun openPrivatePolicy() = postUiEvent(OpenPrivacyPolicy())

    fun openWeatherApiLicenceInfo()= postUiEvent(OpenApiLicencePage())

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AboutViewModel() as T
        }
    }
}