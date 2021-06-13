package com.potsane.potsaneweatherapp.database

import androidx.room.TypeConverter
import com.potsane.potsaneweatherapp.entity.api.CurrentWeatherInfo
import com.potsane.potsaneweatherapp.entity.api.DailyWeatherInfo
import com.potsane.potsaneweatherapp.entity.api.Weather
import com.potsane.potsaneweatherapp.entity.api.WeatherResponseItem
import com.potsane.potsaneweatherapp.entity.view.LocationInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class EntityConverters {

    @TypeConverter
    fun stringToWeatherResponseItem(weatherResponseItem: String): WeatherResponseItem? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val type = Types.newParameterizedType(
            WeatherResponseItem::class.java,
            CurrentWeatherInfo::class.java,
            List::class.java,
            DailyWeatherInfo::class.java,
            Weather::class.java
        )
        val adapter = moshi.adapter<WeatherResponseItem>(type)
        return adapter.fromJson(weatherResponseItem)
    }

    @TypeConverter
    fun stringToLocationInfo(locationInfo: String): LocationInfo? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val type = Types.newParameterizedType(LocationInfo::class.java, String::class.java)
        val adapter = moshi.adapter<LocationInfo>(type)
        return adapter.fromJson(locationInfo)
    }

    @TypeConverter
    fun locationInfoToString(locationInfo: LocationInfo): String? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val type = Types.newParameterizedType(LocationInfo::class.java, String::class.java)

        val adapter = moshi.adapter<LocationInfo>(type)
        return adapter.toJson(locationInfo)
    }

    @TypeConverter
    fun weatherResponseItemToString(weatherResponseItem: WeatherResponseItem): String? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val type = Types.newParameterizedType(
            WeatherResponseItem::class.java,
            CurrentWeatherInfo::class.java,
            List::class.java,
            DailyWeatherInfo::class.java,
            Weather::class.java
        )

        val adapter = moshi.adapter<WeatherResponseItem>(type)
        return adapter.toJson(weatherResponseItem)
    }
}