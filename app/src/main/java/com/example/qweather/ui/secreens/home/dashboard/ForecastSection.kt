package com.example.qweather.ui.secreens.home.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import com.example.qweather.domain.dto.forecast.DailyWeatherDto
import com.example.qweather.ui.theme.MyAppTheme


@Composable
fun ForecastSection(forecastList: List<DailyWeatherDto>) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            text = context.getString(R.string.forecast),
            style = MyAppTheme.typography.titleMedium.copy(color = Color(0xFF00BFFF))
        )
        Spacer(Modifier.height(8.dp))
        forecastList.forEach { day ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(day.date, Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_favt_active),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(day.weatherType, Modifier.weight(1f))
                Text("${day.temperatureMax}/${day.temperature}°C", Modifier.weight(1f))
            }
        }
        Button(
            onClick = { /* TODO: View details */ },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(context.getString(R.string.view_details))
        }
    }
}