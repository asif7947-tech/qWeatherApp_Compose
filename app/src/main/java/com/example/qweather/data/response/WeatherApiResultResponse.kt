package com.example.qweather.data.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable


@Keep
data class WeatherApiResultResponse<T>(
    @Json(name = "Response")
    val Response: WeatherApiResponse<T>
)
