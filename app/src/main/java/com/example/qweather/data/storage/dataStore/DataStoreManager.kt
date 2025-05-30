package com.example.qweather.data.storage.dataStore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

// Extension to create DataStore
val Context.dataStore by preferencesDataStore(name = "app_preferences")

class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val selectedLanguage = stringPreferencesKey("current_selected_language")
    private val selectedCityId = stringPreferencesKey("current_selected_city_id")
    private val selectedCityName = stringPreferencesKey("current_selected_city_name")


    fun saveSelectedLanguage(language: String) {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit { preferences ->
                preferences[selectedLanguage] = language
            }
        }
    }

    fun getSelectedLanguage(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[selectedLanguage] ?: ""
        }
    }

    fun saveSelectedCityId(cityId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit { preferences ->
                preferences[selectedCityId] = cityId.toString()
            }
        }
    }

    fun saveSelectedCityName(cityName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit { preferences ->
                preferences[selectedCityName] = cityName
            }
        }
    }

    fun getSelectedCityId(): Flow<Long> {
        return context.dataStore.data.map { preferences ->
            preferences[selectedCityId]?.toLong() ?: 0L
        }
    }

    fun getSelectedCityName(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[selectedCityName] ?: ""
        }
    }

}
