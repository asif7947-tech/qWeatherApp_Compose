package com.example.qweather.data.network

import com.example.qweather.data.response.WeatherApiResultResponse
import com.example.qweather.data.response.city.CityResultResponse
import com.example.qweather.data.response.forecast.ForecastResult
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherApiService {

    @POST("cities")
    suspend fun getCities(): WeatherApiResultResponse<CityResultResponse>

    @POST("current_weather/city")
    suspend fun getForecast(@Query("city_id") cityId: Int): WeatherApiResultResponse<ForecastResult>
}