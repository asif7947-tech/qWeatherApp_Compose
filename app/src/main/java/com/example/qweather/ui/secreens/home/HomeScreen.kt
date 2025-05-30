package com.example.qweather.ui.secreens.home

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.qweather.R
import com.example.qweather.ui.component.CityBottomSheet
import com.example.qweather.ui.components.LanguageSelector
import com.example.qweather.ui.secreens.home.component.DrawerItem
import com.example.qweather.ui.secreens.home.component.SunriseSunsetChart
import com.example.qweather.ui.secreens.home.component.WeatherInfoCard
import com.example.qweather.ui.secreens.home.component.WeatherMetricsRow
import com.example.qweather.ui.theme.AppTheme
import com.example.qweather.ui.theme.MyAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, weatherViewModel: WeatherViewModel = hiltViewModel()) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val showBottomSheet = remember { mutableStateOf(false) }
    val selectedCity = remember { mutableStateOf("Test") }

    val selectedCityState = weatherViewModel.selectedCityName.collectAsState()
    val selectedCityIdState = weatherViewModel.selectedCityId.collectAsState()

    LaunchedEffect(selectedCityState.value) {
        selectedCity.value = selectedCityState.value
        weatherViewModel.loadForecast(selectedCityIdState.value)
    }

    val context = navController.context

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent()
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MyAppTheme.colorScheme.background),
                    title = {
                        Row(
                            modifier = Modifier.fillMaxSize().padding(top = 20.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier.clickable {
                                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                                    showBottomSheet.value = !showBottomSheet.value
                                },
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = selectedCity.value, modifier = Modifier.padding(start = 4.dp),
                                color = Color.White, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(10.dp))
                                Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White)
                            }
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* chat icon click */ }) {
                            Icon(Icons.Default.MailOutline, contentDescription = "Chat" , tint = Color.White)
                        }
                    },
                )
            },
            containerColor = MyAppTheme.colorScheme.background // Page background
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize().background(Color(0xFF3A003B))
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {

                WeatherInfoCard()
                WeatherMetricsRow()
                SunriseSunsetChart()
            }
        }
    }

    if (showBottomSheet.value) {
        CityBottomSheet(
            selectedTab = "Tab 1",
            onDismissRequest = { showBottomSheet.value = false }
        )
    }
}

@Composable
fun DrawerContent( viewModel: WeatherViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val currentLocale = remember { mutableStateOf("en") }

    val selectedLanguageState = viewModel.selectedLanguage.collectAsState()


    LaunchedEffect(selectedLanguageState.value) {
        currentLocale.value = selectedLanguageState.value
    }

    Column(modifier = Modifier.fillMaxSize().padding(top = 30.dp, end = 80.dp).background(Color.White)) {
           Column(
               modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp),
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               // Circular Icon with Border
               Box(
                   modifier = Modifier
                       .size(110.dp)
                       .border(4.dp, MyAppTheme.colorScheme.primary, CircleShape)
                       .clip(CircleShape)
                       .background(MaterialTheme.colorScheme.surface),
                   contentAlignment = Alignment.Center
               ) {
                   Image(
                       painter = painterResource(R.drawable.app_icon),// Replace with actual app icon
                       contentDescription = "App Icon",
                       contentScale = ContentScale.Crop
                   )
               }

               Spacer(modifier = Modifier.height(12.dp))

               Text(
                   text = context.getString(R.string.app_name),
                   style = MaterialTheme.typography.titleLarge.copy(
                       fontWeight = FontWeight.Bold
                   )
               )
           }
        Divider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Drawer Options
        DrawerItem(
            title = context.getString(R.string.share_app),
            leadingIcon = Icons.Default.Share,
            onClick = {
            }
        )

        DrawerItem(
            title = context.getString(R.string.rate_app),
            leadingIcon = Icons.Default.Star,
            onClick = {

            }
        )

        // Language Toggle
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )

        Spacer(modifier = Modifier.height(16.dp))
        LanguageSelector(
            currentLanguage = selectedLanguageState.value,
            onLanguageSelected = { newLanguage ->
                viewModel.updateLanguage(newLanguage)
                (context as? Activity)?.recreate()
            }
        )

        }
        // Add more as needed
    }
