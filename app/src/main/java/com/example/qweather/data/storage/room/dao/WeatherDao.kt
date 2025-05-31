package com.example.qweather.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.qweather.data.storage.room.entities.CurrentCityEntity
import com.example.qweather.data.storage.room.entities.FavouriteCitiesEntity
import com.example.qweather.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentCity(currentCityEntity: CurrentCityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteCity(sound: FavouriteCitiesEntity)

    @Query("SELECT * FROM ${Constants.SELECTED_CITY} LIMIT 1")
    suspend fun getSelectedCity(): CurrentCityEntity?

    @Query("SELECT * FROM ${Constants.FAVOURITE_CITIES_TABLE}")
    fun getFavoriteCities(): Flow<List<FavouriteCitiesEntity>>

    @Query("DELETE FROM ${Constants.FAVOURITE_CITIES_TABLE} WHERE cityId = :id")
    suspend fun deleteCity(id: Int)
}