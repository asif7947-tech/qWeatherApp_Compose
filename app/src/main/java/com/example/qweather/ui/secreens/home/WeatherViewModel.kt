package com.example.qweather.ui.secreens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qweather.data.storage.room.entities.FavouriteCitiesEntity
import com.example.qweather.domain.dto.cities.CityDataDto
import com.example.qweather.domain.dto.forecast.ForecastResultDto
import com.example.qweather.domain.repository.LocalStorageRepository
import com.example.qweather.domain.repository.WeatherRepository
import com.example.qweather.utils.LocaleUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val localStorageRepository: LocalStorageRepository
) : ViewModel() {

    // Main Cities State
    private val _myMainCities = MutableStateFlow<List<CityDataDto>>(emptyList())
    val mainCities: StateFlow<List<CityDataDto>> = _myMainCities

    // World Wide Cities State
    private val _mWorldWideCities = MutableStateFlow<List<CityDataDto>>(emptyList())
    val worldWideCities: StateFlow<List<CityDataDto>> = _mWorldWideCities
    // Current City ID State
    private val _selectedCityId = MutableStateFlow(0) // Default filter ID
    val selectedCityId: StateFlow<Int> = _selectedCityId

    // Current City Name State
    private val _selectedCityName = MutableStateFlow("") // Default filter ID
    val selectedCityName: StateFlow<String> = _selectedCityName

    // Language State
    private val _selectedLanguage = MutableStateFlow(LocaleUtils.ENGLISH)
    val selectedLanguage: StateFlow<String> = _selectedLanguage

    // Forecast State
    private val _forecastState = MutableStateFlow<WeatherUiState<ForecastResultDto>>(WeatherUiState.Loading)
    val forecastState: StateFlow<WeatherUiState<ForecastResultDto>> = _forecastState.asStateFlow()

    // Favourite Cities State
    private val _favouriteCities = MutableStateFlow<List<FavouriteCitiesEntity>>(emptyList())
    val favouriteCities: StateFlow<List<FavouriteCitiesEntity>> = _favouriteCities

    init {
        loadCities()
        loadSavedLanguage()
    }

    // Load cities
    private fun loadCities() {
        viewModelScope.launch {
            weatherRepository.getCities()
                .catch { e ->
                    Log.e("WeatherViewModel", e.message ?: "Unknown Error")
                }
                .collect { result ->
                    result.onSuccess {
//                        _citiesState.value = WeatherUiState.Success(it)
                        _myMainCities.value = it.cities
                        _mWorldWideCities.value = it.worldCities
                        Log.e("WeatherViewModel success: ", it.cities.toString())
                        fetchSelectedCity()
                        }
                        .onFailure {
                            Log.e("WeatherViewModel Error: ", it.message ?: "Failed to load cities")
                        }
                }
        }
    }


    // fetch Db Data
   private fun fetchSelectedCity() {
        viewModelScope.launch {
            val selectedCity = localStorageRepository.getCurrentCity()
            if (selectedCity != null){
                _selectedCityId.value = selectedCity.id
                _selectedCityName.value = selectedCity.name
            } else {
                _selectedCityId.value = _myMainCities.value[0].id
                _selectedCityName.value = _myMainCities.value[0].name
            }
        }
    }

    // Load forecast
    fun loadForecast(cityId: Int) {
        viewModelScope.launch {
            _forecastState.value = WeatherUiState.Loading
            weatherRepository.getForecast(cityId)
                .catch { e ->
                    _forecastState.value = WeatherUiState.Error(e.message ?: "Unknown Error")
                }
                .collect { result ->
                    result
                        .onSuccess {
                            Log.e("WeatherViewModel success: ", it.toString())
                            _forecastState.value = WeatherUiState.Success(it)
                        }
                        .onFailure {
                            _forecastState.value = WeatherUiState.Error(it.message ?: "Failed to load forecast")
                        }
                }
        }
    }

    // Set selected city
    fun onSetSelectedCity(city: CityDataDto) {
        _selectedCityId.value = city.id
        _selectedCityName.value = city.name
        CoroutineScope(Dispatchers.IO).launch {
            localStorageRepository.saveCurrentCity(city)
        }
    }

    private fun loadSavedLanguage() {
        viewModelScope.launch {
            localStorageRepository.getSelectedLanguage().collect { language ->
                if (language.isNotEmpty()) {
                    _selectedLanguage.value = language
                }
            }
        }
    }

    fun onAddToFavourite(city: CityDataDto) {
        viewModelScope.launch {
            localStorageRepository.addToFavourite(city)
        }
    }

    fun onRemoveFromFavourite(city_id: Int) {
        viewModelScope.launch {
            localStorageRepository.removeFromFavourite(city_id)
        }
    }

    fun getFavouriteCities() {
        viewModelScope.launch {
            localStorageRepository.getFavouriteCities().collect { cities ->
                _favouriteCities.value = cities
            }
        }
    }

    fun updateLanguage(language: String) {
        viewModelScope.launch {
            _selectedLanguage.value = language
            localStorageRepository.saveSelectedLanguage(language)
        }
    }
}
