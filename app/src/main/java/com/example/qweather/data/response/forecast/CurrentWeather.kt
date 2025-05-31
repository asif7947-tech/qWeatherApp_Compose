package com.example.qweather.data.response.forecast

import androidx.annotation.Keep
import com.example.qweather.domain.dto.forecast.CurrentWeatherDto
import com.squareup.moshi.Json

@Keep
data class CurrentWeather(
     @Json(name = "clouds")
    val clouds: Int,
     @Json(name = "feels_like")
    val feelsLike: Double,
     @Json(name = "feels_like_unit")
    val feelsLikeUnit: String,
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
     @Json(name = "sunrise")
    val sunrise: Int,
     @Json(name = "sunset")
    val sunset: Int,
     @Json(name = "temperature")
    val temperature: Double,
     @Json(name = "temperature_max")
    val temperatureMax: Double,
     @Json(name = "temperature_min")
    val temperatureMin: Double,
     @Json(name = "temperature_unit")
    val temperatureUnit: String,
     @Json(name = "time")
    val time: Long,
     @Json(name = "uv_index")
    val uvIndex: String,
     @Json(name = "visibility")
    val visibility: Int,
     @Json(name = "visibility_unit")
    val visibilityUnit: String,
     @Json(name = "weather_icon")
    val weatherIcon: String,
     @Json(name = "weather_type")
    val weatherType: String,
     @Json(name = "weather_type_ar")
    val weatherTypeAr: String,
     @Json(name = "wind_direction")
    val windDirection: Int,
     @Json(name = "wind_direction_text")
    val windDirectionText: String,
     @Json(name = "wind_direction_text_ar")
    val windDirectionTextAr: String,
     @Json(name = "wind_power")
    val windPower: Double,
     @Json(name = "wind_power_unit")
    val windPowerUnit: String
) {
    fun toCurrentWeatherDto(): CurrentWeatherDto {
        return CurrentWeatherDto(
            clouds = clouds,
            feelsLike = feelsLike,
            feelsLikeUnit = feelsLikeUnit,
            humidity = humidity,
            humidityUnit = humidityUnit,
            pressure = pressure,
            pressureUnit = pressureUnit,
            rain = rain,
            rainUnit = rainUnit,
            sunrise = sunrise,
            sunset = sunset,
            temperature = temperature,
            temperatureMax = temperatureMax,
            temperatureMin = temperatureMin,
            temperatureUnit = temperatureUnit,
            time = time,
            uvIndex = uvIndex,
            visibility = visibility,
            visibilityUnit = visibilityUnit,
            weatherIcon = weatherIcon,
            weatherType = weatherType,
            weatherTypeAr = weatherTypeAr,
            windDirection = windDirection,
            windDirectionText = windDirectionText,
            windDirectionTextAr = windDirectionTextAr,
            windPower = windPower,
            windPowerUnit = windPowerUnit
        )
    }
}