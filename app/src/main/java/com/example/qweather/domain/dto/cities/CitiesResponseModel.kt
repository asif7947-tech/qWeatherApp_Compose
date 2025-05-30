package com.example.qweather.domain.dto.cities

data class CitiesResponseModel(
    val cities: List<CityDataModel>,
    val worldCities: List<CityDataModel>
){

}
