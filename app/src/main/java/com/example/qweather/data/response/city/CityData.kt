package com.example.qweather.data.response.city


import androidx.annotation.Keep
import com.example.qweather.domain.dto.cities.CityDataModel
import com.squareup.moshi.Json

@Keep
data class CityData(
    @Json(name = "city_id")
    val cityId: Int,
     @Json(name = "country")
    val country: String,
     @Json(name = "country_name")
    val countryName: String,
     @Json(name = "latitude")
    val latitude: Double,
     @Json(name = "longitude")
    val longitude: Double,
     @Json(name = "name")
    val name: String,
     @Json(name = "name_ar")
    val nameAr: String,
     @Json(name = "country_name_ar")
    val countryNameAr: String,
){
    fun toCityDataModel(): CityDataModel {
        return CityDataModel(
            id = cityId,
            country = country,
            countryName = countryName,
            latitude = latitude,
            longitude = longitude,
            name = name,
            cityNameAr = nameAr,
            countryNameAr = countryNameAr
        )
    }
}