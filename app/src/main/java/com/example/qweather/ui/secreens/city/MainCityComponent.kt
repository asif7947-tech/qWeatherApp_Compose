package com.example.qweather.ui.secreens.city

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.qweather.ui.secreens.home.LocationViewModel
import com.example.qweather.ui.secreens.home.WeatherViewModel
import com.example.qweather.ui.theme.MyAppTheme


/**
 * @author Asif Mehmood
 *
 * This is Main City Component of City Screen
 * to show list of Our Maincities
 * */
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainCityComponent(
    viewModel: WeatherViewModel = hiltViewModel(),
    locationViewModel: LocationViewModel = hiltViewModel()
) {

    val cities by viewModel.mainCities.collectAsState()
    val context: Context = LocalContext.current
    val selectedCityIdState = viewModel.selectedCityId.collectAsState()
    val currentCityState = locationViewModel.currentCityState.collectAsState()
    val currentCountryState = locationViewModel.currentCountryState.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 16.dp)) {
        if (currentCityState.value != null && currentCountryState.value != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        color = Color(0xFFEFEFEF)
                    )
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Current - Location",
                    style = MyAppTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = MyAppTheme.colorScheme.appTextColorWhite
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${currentCityState.value}, ${currentCountryState.value}",
                    style = MyAppTheme.typography.titleMedium,
                    color = MyAppTheme.colorScheme.black
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Qatar - Cities",
            style = MyAppTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.fillMaxWidth().background(Color(0xFFEFEFEF)).padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(cities) { city ->
                Column(
                    Modifier
                        .clickable {
                            Toast.makeText(context, "You a City: ${city.name} as Default City", Toast.LENGTH_SHORT).show()
                            viewModel.onSetSelectedCity(city)
                        }
                        .fillMaxWidth()
                        .background(
                            if (city.id == selectedCityIdState.value) Color(0xFFDC594F) else Color.White
                        ),
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = city.name ?: "Unknown",
                            style = MyAppTheme.typography.titleMedium
                        )
                        Divider(
                            thickness = 1.dp,
                            color = Color(0xFFEFEFEF)
                        )
                    }
                }
            }
        }

    }
}