package com.potsane.potsaneweatherapp.common.listener

import com.potsane.potsaneweatherapp.entity.view.LocationInfo

interface OnLocationItemClickListener {
    fun onViewWeatherInfoForLocation(locationInfo : LocationInfo)
    fun onDeleteWeatherInfoForLocation(locationInfo : LocationInfo)
}