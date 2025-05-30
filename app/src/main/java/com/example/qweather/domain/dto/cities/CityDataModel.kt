package com.example.qweather.domain.dto.cities

data class CityDataModel(
    val id: Int,
    val name: String,
    val cityNameAr: String,
    val country: String,
    val countryName: String,
    val countryNameAr: String,
    val latitude: Double,
    val longitude: Double,
)
