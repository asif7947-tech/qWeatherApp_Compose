package com.example.qweather.domain.dto.forecast

data class DayDetailDto(
    val humidity: Int,
    val humidityUnit: String,
    val pressure: Int,
    val pressureUnit: String,
    val rain: Int,
    val rainUnit: String,
    val temperature: Int,
    val temperatureUnit: String,
    val time: String,
    val timeHrQatar: String,
    val timestamp: Int,
    val visibility: Int,
    val visibilityUnit: String,
    val warningText: String,
    val warningTextAr: String,
    val weatherType: String,
    val weatherTypeAr: String,
    val windDirection: Int,
    val windDirectionText: String,
    val windPower: Int,
    val windPowerUnit: String
)
