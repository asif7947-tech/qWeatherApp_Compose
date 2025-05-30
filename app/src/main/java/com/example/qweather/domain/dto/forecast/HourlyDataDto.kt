package com.example.qweather.domain.dto.forecast

data class HourlyDataDto(
    val date: String,
    val dayDetails: List<DayDetailDto>
)
