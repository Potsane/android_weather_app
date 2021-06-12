package com.potsane.potsaneweatherapp

import android.app.Application
import android.content.Context
import com.google.android.libraries.places.api.Places
import com.potsane.potsaneweatherapp.BuildConfig.PLACES_API_KEY

class PotsaneWeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        Places.initialize(applicationContext, PLACES_API_KEY)

    }

    companion object {
        @Volatile
        private var context: Context? = null

        @JvmStatic
        fun getAppContext(): Context? = context
    }
}