package com.example.qweather.data.response.city


import androidx.annotation.Keep
import com.example.qweather.domain.dto.cities.CitiesResponseDto
import com.squareup.moshi.Json

@Keep
data class CitiesDataResponse(
    @Json(name = "qatar")
    val qatar: List<CityData>,
    @Json(name = "world")
    val world: List<CityData>
){
    fun toCitiesResponseModel(): CitiesResponseDto {
        return CitiesResponseDto(
            cities = qatar.map { it.toCityDataModel() },
            worldCities = world.map { it.toCityDataModel() }
        )
    }
}