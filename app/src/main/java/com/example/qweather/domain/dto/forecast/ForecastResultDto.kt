package com.example.qweather.domain.dto.forecast

import com.example.qweather.data.response.forecast.CurrentWeather
import com.example.qweather.data.response.forecast.DailyWeather
import com.example.qweather.data.response.forecast.HourlyData

data class ForecastResultDto(
    val cityId: Int,
    val countryName: String,
    val country: String,
    val currentWeather: CurrentWeather,
    val dailyWeather: List<DailyWeather>,
    val hourlyData: List<HourlyData>,
    val latitude: Double,
    val longitude: Double,
)
