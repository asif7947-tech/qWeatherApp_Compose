package com.example.qweather.data.repository

import android.util.Log
import com.example.qweather.data.network.WeatherApiService
import com.example.qweather.data.response.forecast.ForecastResult
import com.example.qweather.domain.dto.cities.CitiesResponseModel
import com.example.qweather.domain.repository.WeatherRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApiService: WeatherApiService
) : WeatherRepository {

    //  Fetch cities from API
    override suspend fun getCities(): Flow<Result<CitiesResponseModel>> = flow {
        try {
            val apiResponse = weatherApiService.getCities()
            if (apiResponse.Response.status == true) {
                Log.e("WeatherRepositoryImpl", Gson().toJson(apiResponse.Response.result))
                emit(Result.success(apiResponse.Response.result.cities.toCitiesResponseModel()))
            } else {
                Log.e("WeatherRepositoryImpl", "response is null")
                emit(Result.failure(Exception("API returned false status")))
            }
        } catch (e: Exception) {
            Log.e("WeatherRepositoryImpl", e.message.toString())
            emit(Result.failure(e))
        }

    }

    //  Fetch forecast from API
    override suspend fun getForecast(cityId: Long): Flow<Result<ForecastResult>> = flow {
        try {
            val apiResponse = weatherApiService.getForecast(cityId)
            Log.e("WeatherRepositoryImpl RESDDDT", Gson().toJson(apiResponse.Response.result))

            if (apiResponse.Response.status == true) {
                emit(Result.success(apiResponse.Response.result))
            } else {
                emit(Result.failure(Exception("API returned false status")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}