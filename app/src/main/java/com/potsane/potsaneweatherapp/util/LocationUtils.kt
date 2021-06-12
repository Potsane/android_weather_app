package com.potsane.potsaneweatherapp.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.net.ConnectivityManager
import java.util.*

object LocationUtils {

    fun getLocationName(lat: Double, lon: Double, context: Context): List<Address>? {
        val geocoder = Geocoder(context, Locale.getDefault())
        return geocoder.getFromLocation(lat, lon, 1)
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
