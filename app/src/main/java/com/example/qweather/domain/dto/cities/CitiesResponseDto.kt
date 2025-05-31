package com.example.qweather.domain.dto.cities

data class CitiesResponseDto(
    val cities: List<CityDataDto>,
    val worldCities: List<CityDataDto>
){

}
