package com.potsane.potsaneweatherapp.ui.location

import androidx.lifecycle.*
import com.potsane.potsaneweatherapp.common.listener.OnLocationItemClickListener
import com.potsane.potsaneweatherapp.entity.view.LocationInfo
import com.potsane.potsaneweatherapp.injection.Injector
import com.potsane.potsaneweatherapp.repository.WeatherInfoRepository
import com.potsane.potsaneweatherapp.ui.base.BaseWeatherAppViewModel
import com.potsane.potsaneweatherapp.ui.base.HideProgressBar
import com.potsane.potsaneweatherapp.ui.base.ShowProgressBar
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val weatherInfoRepository: WeatherInfoRepository
) : BaseWeatherAppViewModel(), OnLocationItemClickListener {

    private val _locations = MutableLiveData<List<LocationInfo>>()
    val locations: LiveData<List<LocationInfo>> = _locations

    init {
        getLocations()
    }

    private fun getLocations() {
        val locationsList = mutableListOf<LocationInfo>()
        viewModelScope.launch {
            postUiEvent(ShowProgressBar())
            weatherInfoRepository.fetchWeatherInfoForAllLocations().let {
                if (it != null) {
                    for (index in it.indices) {
                        locationsList.add(it[index].locationInfo)
                    }
                    _locations.value = locationsList
                }
            }
            postUiEvent(HideProgressBar())
        }
    }

    override fun onViewWeatherInfoForLocation(locationInfo: LocationInfo) {
        postUiEvent(ShowWeatherInfoForLocation(locationInfo))
    }

    override fun onDeleteWeatherInfoForLocation(locationInfo: LocationInfo) {
        postUiEvent(DeleteWeatherInfoForLocation(locationInfo))
    }

    fun deleteLocation(locationInfo: LocationInfo) {
        val locationsList = mutableListOf<LocationInfo>()
        viewModelScope.launch {
            weatherInfoRepository.deleteWeatherInfoForLocation(locationInfo).let {
                for (index in it.indices) {
                    locationsList.add(it[index].locationInfo)
                }
                _locations.value = locationsList
            }
        }
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LocationsViewModel(
                Injector.weatherInfoRepository
            ) as T
        }
    }
}