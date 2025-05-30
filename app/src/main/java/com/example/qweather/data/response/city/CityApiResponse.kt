package com.example.qweather.data.response.city

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class CityApiResponse(
     @Json(name = "Response")
    val Response: WeatherResponseResult
)

@Keep
data class WeatherResponseResult(
     @Json(name = "result")
    val result: CityResultResponse,
     @Json(name = "status")
     val status: Boolean
)
