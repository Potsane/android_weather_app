package com.potsane.potsaneweatherapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.potsane.potsaneweatherapp.entity.api.WeatherResponseItem
import com.potsane.potsaneweatherapp.entity.view.LocationInfo

@Entity
data class WeatherInfoEntity(

    @PrimaryKey
    @ColumnInfo(name = "location_name")
    val locationName: String,

    @ColumnInfo(name = "weather_info")
    val weatherResponseItem: WeatherResponseItem,

    @ColumnInfo(name = "storage_time_stamp")
    val storageTimestamp: Long,

    @ColumnInfo(name = "location_info")
    val locationInfo: LocationInfo
)