package com.potsane.potsaneweatherapp.util

import android.content.Context
import android.location.Geocoder
import android.net.ConnectivityManager
import com.potsane.potsaneweatherapp.entity.view.LocationInfo
import java.util.*

object LocationUtils {

    private const val DEFAULT_LAT = 26.2041
    private const val DEFAULT_LON = 28.0473

    fun getLocalizedLocationInfo(lat: Double?, lon: Double?, context: Context): LocationInfo {
        val geocoder = Geocoder(context, Locale.getDefault())
        val geocoderLocationInfo = geocoder.getFromLocation(
            lat ?: DEFAULT_LAT,
            lon ?: DEFAULT_LON,
            1
        )
        val currentLocation = geocoderLocationInfo[0]
        return LocationInfo(
            currentLocation.locality,
            currentLocation.latitude,
            currentLocation.latitude
        )
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
