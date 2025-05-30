package com.example.qweather.ui.secreens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qweather.data.response.city.CityResultResponse
import com.example.qweather.data.response.forecast.ForecastResult
import com.example.qweather.domain.dto.cities.CityDataModel
import com.example.qweather.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _myMainCities = MutableStateFlow<List<CityDataModel>>(emptyList())
    val mainCities: StateFlow<List<CityDataModel>> = _myMainCities

    private val _mWorldWideCities = MutableStateFlow<List<CityDataModel>>(emptyList())
    val worldWideCities: StateFlow<List<CityDataModel>> = _mWorldWideCities
    // Cities State
    private val _citiesState = MutableStateFlow<WeatherUiState<CityResultResponse>>(WeatherUiState.Loading)
    val citiesState: StateFlow<WeatherUiState<CityResultResponse>> = _citiesState.asStateFlow()

    // Forecast State
    private val _forecastState = MutableStateFlow<WeatherUiState<ForecastResult>>(WeatherUiState.Loading)
    val forecastState: StateFlow<WeatherUiState<ForecastResult>> = _forecastState.asStateFlow()

    init {
        loadCities()
        loadForecast(290030)
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
                        }
                        .onFailure {
                            _citiesState.value = WeatherUiState.Error(it.message ?: "Failed to load cities")
                            Log.e("WeatherViewModel Error: ", it.message ?: "Failed to load cities")
                        }
                }
        }
    }

    // Load forecast
    fun loadForecast(cityId: Long) {
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
}
