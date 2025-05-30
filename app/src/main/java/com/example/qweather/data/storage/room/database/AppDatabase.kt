package com.example.qweather.data.storage.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.qweather.data.storage.room.dao.WeatherDao
import com.example.qweather.data.storage.room.entities.CurrentCityEntity
import com.example.qweather.data.storage.room.entities.FavouriteCitiesEntity

@Database(
    entities = [FavouriteCitiesEntity::class, CurrentCityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
