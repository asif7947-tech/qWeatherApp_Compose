package com.example.qweather.data.response.forecast


import androidx.annotation.Keep
import com.example.qweather.domain.dto.forecast.ForecastResultDto
import com.squareup.moshi.Json

@Keep
data class ForecastResult(
     @Json(name = "city_id")
    val cityId: Int,
     @Json(name = "country")
    val country: String,
     @Json(name = "country_name")
    val countryName: String,
     @Json(name = "current_weather")
    val currentWeather: CurrentWeather,
     @Json(name = "daily_weather")
    val dailyWeather: List<DailyWeather>,
     @Json(name = "hourly_data")
    val hourlyData: List<HourlyData>,
     @Json(name = "latitude")
    val latitude: Double,
     @Json(name = "longitude")
    val longitude: Double,
     @Json(name = "name")
    val name: String,
     @Json(name = "utc_offset")
    val utcOffset: String
) {

    fun toForecastResultDto(): ForecastResultDto {
        return ForecastResultDto(
            cityId = cityId,
            country = country,
            countryName = countryName,
            currentWeather = currentWeather.toCurrentWeatherDto(),
            dailyWeather = dailyWeather.map { it.toDailyWeatherDto() },
            hourlyData = hourlyData.map { it.toHourlyDataDto() },
            latitude = latitude,
            longitude = longitude,
        )
    }
}