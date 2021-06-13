package com.potsane.potsaneweatherapp.database

import androidx.room.TypeConverter
import com.potsane.potsaneweatherapp.entity.api.CurrentWeatherInfo
import com.potsane.potsaneweatherapp.entity.api.DailyWeatherInfo
import com.potsane.potsaneweatherapp.entity.api.Weather
import com.potsane.potsaneweatherapp.entity.api.WeatherResponseItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class EntityConverters {

    /*  fun stringToWeatherInfoEntity(weatherInfoEntity: String): WeatherInfoEntity? {
          val moshi = Moshi.Builder()
              .add(KotlinJsonAdapterFactory())
              .build()

          val type = Types.newParameterizedType(
              WeatherResponseItem::class.java
          )
          val adapter = moshi.adapter<WeatherInfoEntity>(type)
          return adapter.fromJson(weatherInfoEntity)
      }

      fun weatherInfoEntityToString(weatherInfoEntity : )*/

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

    /* fun stringToCurrentWeatherInfo(currentWeatherInfo: String): CurrentWeatherInfo? {
         val moshi = Moshi.Builder()
             .add(KotlinJsonAdapterFactory())
             .build()
         val type = Types.newParameterizedType(Weather::class.java)
         val adapter = moshi.adapter<CurrentWeatherInfo>(type)
         return adapter.fromJson(currentWeatherInfo)
     }

     fun currentWeatherInfoToString(currentWeatherInfo: CurrentWeatherInfo): String? {
         val moshi = Moshi.Builder()
             .add(KotlinJsonAdapterFactory())
             .build()

         val types = Types.newParameterizedType(Weather::class.java)

         val adapter = moshi.adapter<CurrentWeatherInfo>(types)
         return adapter.toJson(currentWeatherInfo)
     }*/

}