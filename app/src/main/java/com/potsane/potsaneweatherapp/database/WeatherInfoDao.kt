package com.potsane.potsaneweatherapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherInfo(weatherInfoEntity: WeatherInfoEntity)

    @Query("SELECT * FROM weatherinfoentity WHERE location_name =:locationName")
    suspend fun searchLocationWeatherInfo(locationName: String): WeatherInfoEntity?
}