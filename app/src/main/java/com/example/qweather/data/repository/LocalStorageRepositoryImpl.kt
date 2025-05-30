package com.example.qweather.data.repository

import android.content.Context
import com.example.qweather.data.storage.room.dao.WeatherDao
import com.example.qweather.data.storage.room.entities.FavouriteCitiesEntity
import com.example.qweather.domain.dto.cities.CityDataModel
import com.example.qweather.domain.repository.LocalStorageRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalStorageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val weatherDao: WeatherDao
): LocalStorageRepository {
    override suspend fun saveCurrentCity(cities: List<CityDataModel>) {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentCity(): CityDataModel {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavourite(city: CityDataModel) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavourite(city: FavouriteCitiesEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getFavouriteCities(): List<FavouriteCitiesEntity> {
        TODO("Not yet implemented")
    }


}