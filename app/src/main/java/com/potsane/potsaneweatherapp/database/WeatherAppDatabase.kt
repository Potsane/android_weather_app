package com.potsane.potsaneweatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [WeatherInfoEntity::class],
    version = 3
)

@TypeConverters(EntityConverters::class)
abstract class WeatherAppDatabase : RoomDatabase() {

    abstract fun weatherInfoDao(): WeatherInfoDao
}