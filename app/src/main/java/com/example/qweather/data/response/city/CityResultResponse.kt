package com.example.qweather.data.response.city

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class CityResultResponse(
     @Json(name = "cities")
    val cities: CitiesDataResponse
)