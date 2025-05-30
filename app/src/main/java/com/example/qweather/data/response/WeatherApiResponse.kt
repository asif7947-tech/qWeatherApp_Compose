package com.example.qweather.data.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Keep
data class WeatherApiResponse<T>(
     @Json(name = "result")
    val result: T,
     @Json(name = "status")
    val status: Boolean? =null
)
