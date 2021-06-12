package com.potsane.potsaneweatherapp.ui.weather

import android.location.Address
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.potsane.potsaneweatherapp.injection.Injector
import com.potsane.potsaneweatherapp.repository.WeatherInfoRepository
import com.potsane.potsaneweatherapp.ui.base.BaseWeatherAppViewModel
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherInfoRepository: WeatherInfoRepository
) : BaseWeatherAppViewModel() {


    fun fetchWeatherInfo(address: Address?) {
        viewModelScope.launch {
            address?.let {
                weatherInfoRepository.maybeFetchWeatherInfo(address).let {
                    if (it.isSuccessful) {
                        print("=================" + it.body())
                    } else {
                        //showError
                    }
                }
            } ?: run { /*showError*/}
        }
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeatherViewModel(Injector.weatherInfoRepository) as T
        }
    }
}