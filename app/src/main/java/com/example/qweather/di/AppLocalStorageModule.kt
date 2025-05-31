package com.example.qweather.di

import android.content.Context
import androidx.room.Room
import com.example.qweather.data.repository.LocalStorageRepositoryImpl
import com.example.qweather.data.storage.dataStore.DataStoreManager
import com.example.qweather.data.storage.room.dao.WeatherDao
import com.example.qweather.data.storage.room.database.AppDatabase
import com.example.qweather.domain.repository.LocalStorageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppLocalStorageModule {


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "qWeather_app_database"
        )
            .fallbackToDestructiveMigration(false).build()
    }

    @Provides
    fun provideWeatherDao(appDatabase: AppDatabase): WeatherDao {
        return appDatabase.weatherDao()
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Provides
    @Singleton
    fun provideLocalStorageRepository(
        @ApplicationContext context: Context,
        weatherDao: WeatherDao,
        dataStoreManager: DataStoreManager
    ): LocalStorageRepository {
        return LocalStorageRepositoryImpl(context, weatherDao, dataStoreManager)
    }
}