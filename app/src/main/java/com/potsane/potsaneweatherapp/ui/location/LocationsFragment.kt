package com.potsane.potsaneweatherapp.ui.location

import androidx.lifecycle.ViewModelProvider
import com.potsane.potsaneweatherapp.R
import com.potsane.potsaneweatherapp.databinding.FragmentLocationsBinding
import com.potsane.potsaneweatherapp.ui.base.BaseWeatherAppFragment

class LocationsFragment :
    BaseWeatherAppFragment<LocationsViewModel, FragmentLocationsBinding>() {

    override fun getLayoutId() = R.layout.fragment_locations

    override fun createViewModel(): LocationsViewModel {
        return ViewModelProvider(this, LocationsViewModel.Factory())
            .get(LocationsViewModel::class.java)
    }
}