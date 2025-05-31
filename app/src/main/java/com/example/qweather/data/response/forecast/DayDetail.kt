package com.example.qweather.data.response.forecast


import androidx.annotation.Keep
import com.example.qweather.domain.dto.forecast.DayDetailDto
import com.squareup.moshi.Json

@Keep
data class DayDetail(
     @Json(name = "humidity")
    val humidity: Int,
     @Json(name = "humidity_unit")
    val humidityUnit: String,
     @Json(name = "pressure")
    val pressure: Int,
     @Json(name = "pressure_unit")
    val pressureUnit: String,
     @Json(name = "rain")
    val rain: Int,
     @Json(name = "rain_unit")
    val rainUnit: String,
     @Json(name = "temperature")
    val temperature: Int,
     @Json(name = "temperature_unit")
    val temperatureUnit: String,
     @Json(name = "time")
    val time: String,
     @Json(name = "time_hr_qatar")
    val timeHrQatar: String,
     @Json(name = "timestamp")
    val timestamp: Int,
     @Json(name = "visibility")
    val visibility: Int,
     @Json(name = "visibility_unit")
    val visibilityUnit: String,
     @Json(name = "warning_text")
    val warningText: String,
     @Json(name = "warning_text_ar")
    val warningTextAr: String,
     @Json(name = "weather_icon")
    val weatherIcon: String?,
     @Json(name = "weather_type")
    val weatherType: String,
     @Json(name = "weather_type_ar")
    val weatherTypeAr: String,
     @Json(name = "wind_direction")
    val windDirection: Int,
     @Json(name = "wind_direction_text")
    val windDirectionText: String,
     @Json(name = "wind_power")
    val windPower: Int,
     @Json(name = "wind_power_unit")
    val windPowerUnit: String
) {
    fun toDayDetailDto(): DayDetailDto {
        return DayDetailDto(
            humidity = humidity,
            humidityUnit = humidityUnit,
            pressure = pressure,
            pressureUnit = pressureUnit,
            rain = rain,
            rainUnit = rainUnit,
            temperature = temperature,
            temperatureUnit = temperatureUnit,
            time = time,
            timeHrQatar = timeHrQatar,
            timestamp = timestamp,
            visibility = visibility,
            visibilityUnit = visibilityUnit,
            warningText = warningText,
            warningTextAr = warningTextAr,
            weatherType = weatherType,
            weatherTypeAr = weatherTypeAr,
            windDirection = windDirection,
            windDirectionText = windDirectionText,
            windPower = windPower,
            windPowerUnit = windPowerUnit
        )
    }
}