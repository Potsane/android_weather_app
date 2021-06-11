package com.potsane.potsaneweatherapp.util

import android.content.Context
import android.location.Geocoder
import android.net.ConnectivityManager
import java.util.*

object LocationUtils {
    fun getLocationName(
        lat: Double,
        lon: Double,
        context: Context
    ) = Geocoder(context, Locale.getDefault())

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
