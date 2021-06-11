package com.potsane.potsaneweatherapp.ui.weather

import androidx.lifecycle.ViewModelProvider
import com.potsane.potsaneweatherapp.R
import com.potsane.potsaneweatherapp.databinding.FragmentWeatherBinding
import com.potsane.potsaneweatherapp.ui.base.BaseWeatherAppFragment

class WeatherFragment :
    BaseWeatherAppFragment<WeatherViewModel, FragmentWeatherBinding>() {

    override fun getLayoutId() = R.layout.fragment_weather

    override fun createViewModel(): WeatherViewModel {
        return ViewModelProvider(this, WeatherViewModel.Factory()).get(
            WeatherViewModel::class.java
        )
    }
}