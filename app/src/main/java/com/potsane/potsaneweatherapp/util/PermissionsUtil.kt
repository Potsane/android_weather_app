package com.potsane.potsaneweatherapp.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionsUtil {

    const val REQUEST_LOCATION_PERMISSION = 1
    const val PLACES_REQUEST = 2

    private val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private fun hasPermission(permission: String, context: Context?): Boolean {
        return when {
            Build.VERSION.SDK_INT < Build.VERSION_CODES.M -> true
            context == null -> false
            else -> {
                ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }
        }
    }

    fun hasLocationPermissions(context: Context?): Boolean {
        for (permission in locationPermissions) {
            if (!hasPermission(permission, context)) {
                return false
            }
        }
        return true
    }

    fun requestLocationPermissions(activity: Activity, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(activity, locationPermissions, requestCode)
        }
    }
}