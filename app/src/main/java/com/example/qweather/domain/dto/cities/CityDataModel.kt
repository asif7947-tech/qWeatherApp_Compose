package com.example.qweather.domain.dto.cities

import com.example.qweather.data.storage.room.entities.CurrentCityEntity

data class CityDataModel(
    val id: Int,
    val name: String,
    val cityNameAr: String,
    val country: String,
    val countryName: String,
    val countryNameAr: String,
    val latitude: Double,
    val longitude: Double,
){
    fun toCurrentCityEntity(): CurrentCityEntity {
        return CurrentCityEntity(
            id=id,
            name=name,
            country=country,
            countryNameAr = countryNameAr,
            cityNameAr = cityNameAr,
            countryName = countryName,
            latitude = latitude,
            longitude = longitude
        )
    }
}
