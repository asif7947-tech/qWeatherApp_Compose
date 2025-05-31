import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.qweather.ui.secreens.home.WeatherViewModel
import com.example.qweather.ui.theme.MyAppTheme

@Composable
fun WorldWideCityComponent(weatherViewModel: WeatherViewModel= hiltViewModel()) {
    val allWorldWideCities by weatherViewModel.worldWideCities.collectAsState()
    val allFavoriteCities by weatherViewModel.favouriteCities.collectAsState()
    val context: Context = LocalContext.current

    LaunchedEffect(Unit) {
        weatherViewModel.getFavouriteCities()
    }

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        // Search Bar
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Add City") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            },
            trailingIcon = {
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Voice Search"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(
                    shape = RoundedCornerShape(12.dp,)
                ).border(2.dp, MyAppTheme.colorScheme.primary, RoundedCornerShape(12.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFEDEDED),
                unfocusedContainerColor = Color(0xFFEDEDED)
            )
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(allWorldWideCities) { city ->
                Column(
                    Modifier
                        .clickable {
                            weatherViewModel.onAddToFavourite(city)
                        }
                        .fillMaxWidth()
                        .background(Color.White),
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

        Spacer(modifier = Modifier.height(24.dp))

        // Favorites Label
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEDEDED))
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "Favorites",
                style = MyAppTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(allFavoriteCities) { favoriteCity ->
                Column(
                    Modifier
                        .clickable {
                            weatherViewModel.onRemoveFromFavourite(favoriteCity.cityId)
                        }
                        .fillMaxWidth()
                        .background(Color.White),
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
                            text = favoriteCity.cityName ?: "Unknown",
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