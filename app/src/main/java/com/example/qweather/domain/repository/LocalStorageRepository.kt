package com.example.qweather.domain.repository

import com.example.qweather.data.storage.room.entities.FavouriteCitiesEntity
import com.example.qweather.domain.dto.cities.CityDataDto
import kotlinx.coroutines.flow.Flow

interface LocalStorageRepository {

    suspend fun saveCurrentCity(cities: CityDataDto)
    suspend fun getCurrentCity(): CityDataDto?
    suspend fun addToFavourite(city: CityDataDto)
    suspend fun removeFromFavourite(cityId: Int)
    suspend fun getFavouriteCities(): Flow<List<FavouriteCitiesEntity>>
    suspend fun saveSelectedLanguage(language: String)
    suspend fun getSelectedLanguage(): Flow<String>
}