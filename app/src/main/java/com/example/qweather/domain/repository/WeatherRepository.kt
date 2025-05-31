package com.example.qweather.domain.repository

import com.example.qweather.domain.dto.cities.CitiesResponseDto
import com.example.qweather.domain.dto.forecast.ForecastResultDto
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
   suspend fun getCities(): Flow<Result<CitiesResponseDto>>
    suspend fun getForecast(cityId: Int): Flow<Result<ForecastResultDto>>
}