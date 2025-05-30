package com.example.qweather.domain.repository

import com.example.qweather.data.response.forecast.ForecastResult
import com.example.qweather.domain.dto.cities.CitiesResponseModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
   suspend fun getCities(): Flow<Result<CitiesResponseModel>>
    suspend fun getForecast(cityId: Long): Flow<Result<ForecastResult>>
}