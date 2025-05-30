package com.example.qweather.ui.secreens.city

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.qweather.ui.secreens.home.WeatherViewModel
import com.example.qweather.ui.theme.MyAppTheme


@Composable
fun MainCityComponent(viewModel: WeatherViewModel= hiltViewModel(), onCitySelected: (String) -> Unit) {

    val cities by viewModel.mainCities.collectAsState()

    Column(
       modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {

        Text(
            text = "Qatar - Cities",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEFEFEF))
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(cities) { city ->
                Column(
                    Modifier.fillMaxWidth()
                        .clickable { onCitySelected(city.name ?: "Unknown") }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(
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