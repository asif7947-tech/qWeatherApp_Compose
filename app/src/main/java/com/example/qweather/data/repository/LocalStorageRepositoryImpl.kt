package com.example.qweather.data.repository

import android.content.Context
import com.example.qweather.data.storage.dataStore.DataStoreManager
import com.example.qweather.data.storage.room.dao.WeatherDao
import com.example.qweather.data.storage.room.entities.FavouriteCitiesEntity
import com.example.qweather.domain.dto.cities.CityDataDto
import com.example.qweather.domain.repository.LocalStorageRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalStorageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val weatherDao: WeatherDao,
    private val dataStoreManager: DataStoreManager
): LocalStorageRepository {

    override suspend fun saveCurrentCity(citie: CityDataDto) {
        weatherDao.insertCurrentCity(citie.toCurrentCityEntity())
    }

    override suspend fun getCurrentCity(): CityDataDto? {
        val currentCityEntity = weatherDao.getSelectedCity()
        return currentCityEntity?.toCityDataModel()
    }

    override suspend fun addToFavourite(city: CityDataDto) {
        weatherDao.insertFavouriteCity(
            city.toFavouriteCitiesEntity()
        )
    }

    override suspend fun removeFromFavourite(cityId: Int) {
        weatherDao.deleteCity(cityId)
    }

    override suspend fun getFavouriteCities(): Flow<List<FavouriteCitiesEntity>> {
        return weatherDao.getFavoriteCities()
    }

    override suspend fun saveSelectedLanguage(language: String) {
        dataStoreManager.saveSelectedLanguage(language)
    }

    override suspend fun getSelectedLanguage(): Flow<String> {
        return dataStoreManager.getSelectedLanguage()
    }

}