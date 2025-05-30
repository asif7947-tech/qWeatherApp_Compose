package com.example.qweather.domain.repository

import com.example.qweather.data.storage.room.entities.FavouriteCitiesEntity
import com.example.qweather.domain.dto.cities.CityDataModel
import kotlinx.coroutines.flow.Flow

interface LocalStorageRepository {

    suspend fun saveCurrentCity(cities: CityDataModel)
    suspend fun getCurrentCity(): CityDataModel?
    suspend fun addToFavourite(city: CityDataModel)
    suspend fun removeFromFavourite(city: FavouriteCitiesEntity)
    suspend fun getFavouriteCities(): List<FavouriteCitiesEntity>
    suspend fun saveSelectedLanguage(language: String)
    suspend fun getSelectedLanguage(): Flow<String>
}