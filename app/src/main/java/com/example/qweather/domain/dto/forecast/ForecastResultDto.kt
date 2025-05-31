package com.example.qweather.domain.dto.forecast

data class ForecastResultDto(
    val cityId: Int,
    val countryName: String,
    val country: String,
    val currentWeather: CurrentWeatherDto,
    val dailyWeather: List<DailyWeatherDto>,
    val hourlyData: List<HourlyDataDto>,
    val latitude: Double,
    val longitude: Double,
)
