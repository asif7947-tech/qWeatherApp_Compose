package com.example.qweather.data.response.forecast

import kotlinx.serialization.Serializable
import androidx.annotation.Keep
import com.example.qweather.domain.dto.forecast.HourlyDataDto
import com.squareup.moshi.Json

@Keep
@Serializable
data class HourlyData(
     @Json(name = "date")
    val date: String,
     @Json(name = "day_details")
    val dayDetails: List<DayDetail>
) {
    fun toHourlyDataDto(): HourlyDataDto {
        return HourlyDataDto(
            date = date,
            dayDetails = dayDetails.map { it.toDayDetailDto() }
        )
    }
}