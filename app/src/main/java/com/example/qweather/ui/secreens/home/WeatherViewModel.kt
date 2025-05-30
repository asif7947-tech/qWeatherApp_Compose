package com.example.qweather.ui.secreens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qweather.data.response.city.CityResultResponse
import com.example.qweather.data.response.forecast.ForecastResult
import com.example.qweather.domain.dto.cities.CityDataModel
import com.example.qweather.domain.repository.LocalStorageRepository
import com.example.qweather.domain.repository.WeatherRepository
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

    private val _myMainCities = MutableStateFlow<List<CityDataModel>>(emptyList())
    val mainCities: StateFlow<List<CityDataModel>> = _myMainCities

    private val _mWorldWideCities = MutableStateFlow<List<CityDataModel>>(emptyList())
    val worldWideCities: StateFlow<List<CityDataModel>> = _mWorldWideCities
    // Cities State
    private val _citiesState = MutableStateFlow<WeatherUiState<CityResultResponse>>(WeatherUiState.Loading)
    val citiesState: StateFlow<WeatherUiState<CityResultResponse>> = _citiesState.asStateFlow()

    private val _selectedCityId = MutableStateFlow(0) // Default filter ID
    val selectedCityId: StateFlow<Int> = _selectedCityId

    private val _selectedCityName = MutableStateFlow("") // Default filter ID
    val selectedCityName: StateFlow<String> = _selectedCityName

    private val _selectedLanguage = MutableStateFlow("en")
    val selectedLanguage: StateFlow<String> = _selectedLanguage

    // Forecast State
    private val _forecastState = MutableStateFlow<WeatherUiState<ForecastResult>>(WeatherUiState.Loading)
    val forecastState: StateFlow<WeatherUiState<ForecastResult>> = _forecastState.asStateFlow()

    init {
        loadCities()
    }

    // Load cities
    private fun loadCities() {
        viewModelScope.launch {
            _citiesState.value = WeatherUiState.Loading
            weatherRepository.getCities()
                .catch { e ->
                    _citiesState.value = WeatherUiState.Error(e.message ?: "Unknown Error")
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
                            _citiesState.value = WeatherUiState.Error(it.message ?: "Failed to load cities")
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
    fun onSetSelectedCity(city: CityDataModel) {
        _selectedCityId.value = city.id
        _selectedCityName.value = city.name
        CoroutineScope(Dispatchers.IO).launch {
            localStorageRepository.saveCurrentCity(city)
        }
    }


    fun onSetSelectedLanguage(language: String) {
        _selectedLanguage.value = language
        CoroutineScope(Dispatchers.IO).launch {
            localStorageRepository.saveSelectedLanguage(language)
        }
    }
}
