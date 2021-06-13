package com.potsane.potsaneweatherapp.ui.weather

import androidx.lifecycle.*
import com.potsane.potsaneweatherapp.entity.api.WeatherResponseItem
import com.potsane.potsaneweatherapp.entity.view.ExtraWeatherDetail
import com.potsane.potsaneweatherapp.entity.view.LocationInfo
import com.potsane.potsaneweatherapp.injection.Injector
import com.potsane.potsaneweatherapp.repository.WeatherInfoRepository
import com.potsane.potsaneweatherapp.ui.base.BaseWeatherAppViewModel
import com.potsane.potsaneweatherapp.ui.base.HideProgressBar
import com.potsane.potsaneweatherapp.ui.base.ShowProgressBar
import com.potsane.potsaneweatherapp.util.extractExtraWeatherDetails
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherInfoRepository: WeatherInfoRepository
) : BaseWeatherAppViewModel() {

    private val _weatherInfo = MutableLiveData<WeatherResponseItem>()
    val weatherInfo: LiveData<WeatherResponseItem> = _weatherInfo

    private val _locationName = MutableLiveData<String>()
    val locationName: LiveData<String> = _locationName

    private val _extraWeatherDetail = MutableLiveData<List<ExtraWeatherDetail>>()
    val extraWeatherDetail: LiveData<List<ExtraWeatherDetail>> = _extraWeatherDetail

    fun fetchWeatherInfo(locationInfo: LocationInfo?) {
        viewModelScope.launch {
            postUiEvent(ShowProgressBar())
            locationInfo?.let {
                weatherInfoRepository.fetchWeatherInfo(locationInfo).let {
                    _weatherInfo.value = it
                    _locationName.value = locationInfo.locationName
                    _extraWeatherDetail.value = extractExtraWeatherDetails(it?.currentWeatherInfo)
                }
                postUiEvent(HideProgressBar())
            } ?: run { /*showError*/ }
        }
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeatherViewModel(Injector.weatherInfoRepository) as T
        }
    }
}