package com.example.qweather.ui.secreens.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qweather.R


@Composable
fun WeatherInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF70006B)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Amman", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("Thu, 29 May, 11:22 PM", color = Color.White, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.MailOutline, // use a vector drawable
                    contentDescription = "Moon",
                    tint = Color.Yellow,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("18°C", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)
            }

            Text("Clear", color = Color.White, fontSize = 16.sp)
            Text("Feels like 18°", color = Color.White, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                Text("↑ 24°C", color = Color.White, fontSize = 14.sp, modifier = Modifier.weight(1f))
                Text("↓ 14°C", color = Color.White, fontSize = 14.sp)
            }
        }
    }
}


@Composable
fun WeatherMetricsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MetricCard(icon = Icons.Filled.MailOutline, label = "HUMI", value = "44%")
        MetricCard(icon = Icons.Default.LocationOn, label = "W", value = "")
        MetricCard(icon = Icons.Sharp.AddCircle, label = "", value = "4 kt")
    }
}

@Composable
fun SunriseSunsetChart() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Sunrise / Sunset", color = Color(0xFF00AEEF), fontWeight = FontWeight.Bold)
            // Placeholder image or draw Canvas arc
            Image(
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = "Sunrise Sunset Chart",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }
    }
}

@Composable
fun MetricCard(icon: ImageVector, label: String, value: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp ),
        modifier = Modifier
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = label)
            Text(label, fontSize = 12.sp)
            Text(value, fontWeight = FontWeight.Bold)
        }
    }
}

