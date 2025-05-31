package com.example.qweather.ui.secreens.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(): ViewModel() {

    // Location State
    private val _locationState = MutableStateFlow<Location?>(null)
    val locationState: StateFlow<Location?> = _locationState.asStateFlow()

    // Location Error State
    private val _locationErrorState = MutableStateFlow<String?>(null)
    val locationErrorState: StateFlow<String?> = _locationErrorState.asStateFlow()

    private val _currentCityState = MutableStateFlow<String?>(null)
    val currentCityState: StateFlow<String?> = _currentCityState.asStateFlow()

    private val _currentCountryState = MutableStateFlow<String?>(null)
    val currentCountryState: StateFlow<String?> = _currentCountryState.asStateFlow()


    fun getLocation(context: Context) {
        val permission = Manifest.permission.ACCESS_COARSE_LOCATION
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            _locationErrorState.value = "Location permission not granted"
            return
        }
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    Log.e("LocationViewModel", location.toString())
                    Log.e("LocationViewModel", location.latitude.toString())
                    Log.e("LocationViewModel", location.longitude.toString())
                    Log.e("LocationViewModel", location.accuracy.toString())
                    getCityAndCountry(context, location)

                    _locationState.value = location
                }
            }
            .addOnFailureListener {
                _locationErrorState.value = it.message
            }

    }


    private fun getCityAndCountry(context: Context, location: Location) {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val city = addresses[0].locality
                val country = addresses[0].countryName
                _currentCityState.value = city
                _currentCountryState.value = country
            } else {
                _currentCityState.value = null
                _currentCountryState.value = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Pair(null, null)
        }
    }
}