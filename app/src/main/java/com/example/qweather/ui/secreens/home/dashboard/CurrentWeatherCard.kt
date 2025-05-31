package com.example.qweather.ui.secreens.home.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.qweather.R
import com.example.qweather.domain.dto.forecast.CurrentWeatherDto
import com.example.qweather.ui.theme.MyAppTheme
import com.example.qweather.utils.AppUtil


@Composable
fun CurrentWeatherCard(city: String, currentWeather: CurrentWeatherDto) {

    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1976D2), shape = RoundedCornerShape(24.dp))
            .padding(24.dp)
    ) {
        Column {
            Text(city, style = MyAppTheme.typography.headlineLarge, color = Color.White)
            Text(
                text = AppUtil.formatTimestamp(currentWeather.time), // e.g., "Tue, 29 May, 4:38 PM"
                style = MyAppTheme.typography.bodyMedium,
                color = Color.White
            )
            Spacer(Modifier.height(50.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favt_active),
                    contentDescription = null,
                    tint = Color.Yellow,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = "${currentWeather.temperature}°C",
                    style = MyAppTheme.typography.labelLarge,
                    color = Color.White
                )
            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = currentWeather.windDirectionText,
                style = MyAppTheme.typography.bodyLarge,
                color = Color.White
            )
            Text(
                text = context.getString(R.string.feels_like, currentWeather.feelsLike), // "Feels like 41°"
                style = MyAppTheme.typography.bodyMedium,
                color = Color.White
            )
            // Min/Max, humidity, wind, etc.
        }
    }
}