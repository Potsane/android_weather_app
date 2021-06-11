package com.potsane.potsaneweatherapp.injection

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.potsane.potsaneweatherapp.BuildConfig
import com.potsane.potsaneweatherapp.PotsaneWeatherApp
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Injector {

    private val httpLoggingInterceptor: HttpLoggingInterceptor
        get() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val interceptor: Interceptor
        get() {
            return Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .build()
                return@Interceptor chain.proceed(request)
            }
        }

    private val okHttpClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(httpLoggingInterceptor)
                builder.addInterceptor(interceptor)

            }
            return builder.build()
        }

    private val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val applicationContext: Context
        get() = PotsaneWeatherApp.getAppContext()!!

    val fusedLocationClient: FusedLocationProviderClient
        get() = LocationServices.getFusedLocationProviderClient(applicationContext)
}