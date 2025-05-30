package com.example.qweather.ui.secreens.home

sealed class WeatherUiState<out T> {
    object Loading : WeatherUiState<Nothing>()
    data class Success<T>(val data: T) : WeatherUiState<T>()
    data class Error(val message: String) : WeatherUiState<Nothing>()
}