package com.potsane.potsaneweatherapp.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionsUtil {

    const val REQUEST_LOCATION_PERMISSION = 1

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

    fun hasPermissions(permissions: Array<String>, context: Context?): Boolean {
        for (permission in permissions) {
            if (!hasPermission(permission, context)) {
                return false
            }
        }
        return true
    }

    fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode)
        }
    }
}