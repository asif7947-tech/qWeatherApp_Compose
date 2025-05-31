package com.example.qweather.data.response.forecast

import androidx.annotation.Keep
import com.example.qweather.domain.dto.forecast.DailyWeatherDto
import com.squareup.moshi.Json

@Keep
data class DailyWeather(
     @Json(name = "clouds")
    val clouds: Int,
     @Json(name = "date")
    val date: String,
     @Json(name = "feels_like_day")
    val feelsLikeDay: Double,
     @Json(name = "feels_like_eve")
    val feelsLikeEve: Double,
     @Json(name = "feels_like_morn")
    val feelsLikeMorn: Double,
     @Json(name = "feels_like_night")
    val feelsLikeNight: Double,
     @Json(name = "feels_like_unit")
    val feelsLikeUnit: String,
     @Json(name = "humidity")
    val humidity: Double,
     @Json(name = "humidity_min")
    val humidityMin: String,
     @Json(name = "humidity_unit")
    val humidityUnit: String,
     @Json(name = "pop")
    val pop: Int,
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
     @Json(name = "temperature_eve")
    val temperatureEve: Double,
     @Json(name = "temperature_max")
    val temperatureMax: Double,
     @Json(name = "temperature_min")
    val temperatureMin: Double,
     @Json(name = "temperature_morn")
    val temperatureMorn: Double,
     @Json(name = "temperature_night")
    val temperatureNight: Double,
     @Json(name = "temperature_unit")
    val temperatureUnit: String,
     @Json(name = "timestamp")
    val timestamp: Double,
     @Json(name = "weather_icon")
    val weatherIcon: String,
     @Json(name = "weather_type")
    val weatherType: String,
     @Json(name = "weather_type_ar")
    val weatherTypeAr: String,
     @Json(name = "wind_direction")
    val windDirection: Int,
     @Json(name = "wind_speed")
    val windSpeed: Double,
     @Json(name = "wind_speed_unit")
    val windSpeedUnit: String
) {
    fun toDailyWeatherDto(): DailyWeatherDto {
        return DailyWeatherDto(
            clouds = clouds,
            date = date,
            feelsLikeDay = feelsLikeDay,
            feelsLikeEve = feelsLikeEve,
            feelsLikeMorn = feelsLikeMorn,
            feelsLikeNight = feelsLikeNight,
            feelsLikeUnit = feelsLikeUnit,
            humidity = humidity,
            humidityMin = humidityMin,
            humidityUnit = humidityUnit,
            pop = pop,
            pressure = pressure,
            pressureUnit = pressureUnit,
            rain = rain,
            rainUnit = rainUnit,
            sunrise = sunrise,
            sunset = sunset,
            temperature = temperature,
            temperatureEve = temperatureEve,
            temperatureMax = temperatureMax,
            temperatureMin = temperatureMin,
            temperatureMorn = temperatureMorn,
            temperatureNight = temperatureNight,
            temperatureUnit = temperatureUnit,
            timestamp = timestamp,
            weatherIcon = weatherIcon,
            weatherType = weatherType,
            weatherTypeAr = weatherTypeAr,
            windDirection = windDirection,
            windSpeed = windSpeed,
            windSpeedUnit = windSpeedUnit
        )
    }
}