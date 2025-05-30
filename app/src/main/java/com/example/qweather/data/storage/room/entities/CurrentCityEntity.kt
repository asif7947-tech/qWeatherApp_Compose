package com.example.qweather.data.storage.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.qweather.utils.Constants.SELECTED_CITY


@Entity(tableName = SELECTED_CITY)
data class CurrentCityEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val cityNameAr: String,
    val country: String,
    val countryName: String,
    val countryNameAr: String,
    val latitude: Double,
    val longitude: Double,
)
