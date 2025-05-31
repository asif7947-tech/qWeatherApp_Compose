package com.example.qweather.ui.secreens.home

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.BackHandler
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.qweather.R
import com.example.qweather.ui.component.CityBottomSheet
import com.example.qweather.ui.components.LanguageSelector
import com.example.qweather.ui.secreens.home.component.CustomDrawer
import com.example.qweather.ui.secreens.home.component.DrawerItem
import com.example.qweather.ui.secreens.home.dashboard.CurrentWeatherCard
import com.example.qweather.ui.secreens.home.dashboard.ForecastSection
import com.example.qweather.ui.secreens.home.dashboard.WarningCard
import com.example.qweather.ui.secreens.home.states.CustomDrawerState
import com.example.qweather.ui.secreens.home.states.isOpened
import com.example.qweather.ui.secreens.home.states.opposite
import com.example.qweather.ui.theme.MyAppTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, weatherViewModel: WeatherViewModel = hiltViewModel()) {
    var drawerState by remember { mutableStateOf(CustomDrawerState.Closed) }
    val configuration = LocalConfiguration.current
    val coroutineScope = rememberCoroutineScope()

    val density = LocalDensity.current.density

    val screenWidth = remember {
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }
    val offsetValue by remember { derivedStateOf { (screenWidth.value / 3.5).dp } }
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offsetValue else 0.dp,
        label = "Animated Offset"
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.955f else 1f,
        label = "Animated Scale"
    )

    BackHandler(enabled = drawerState.isOpened()) {
        drawerState = CustomDrawerState.Closed
    }

    val showBottomSheet = remember { mutableStateOf(false) }
    val selectedCity = remember { mutableStateOf("Test") }

    val selectedCityState = weatherViewModel.selectedCityName.collectAsState()
    val selectedCityIdState = weatherViewModel.selectedCityId.collectAsState()

    LaunchedEffect(selectedCityState.value) {
        selectedCity.value = selectedCityState.value
        weatherViewModel.loadForecast(selectedCityIdState.value)
    }

    val context = navController.context



    Box(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        CustomDrawer(
            viewModel = weatherViewModel,
            onCloseClick = { drawerState = CustomDrawerState.Closed }
        )

        AppMainContent(
                weatherViewModel = weatherViewModel,
                showBottomSheet = showBottomSheet,
                selectedCity = selectedCity,
            modifier = Modifier.offset(x = animatedOffset).scale(scale = animatedScale),
                drawerState = drawerState,
                onDrawerClick = { drawerState = it }
            )
    }

// Try These for Animation Drawer but it is not working Properly

//    PushDrawer(
//        drawerContent = {  DrawerContent() },
//        mainContent = { openDrawer ->
//            AppMainContent(
//                weatherViewModel = weatherViewModel,
//                showBottomSheet = showBottomSheet,
//                selectedCity = selectedCity,
//                drawerState = drawerState,
//                openDrawer = openDrawer
//            )
//        }
//    )

    if (showBottomSheet.value) {
        CityBottomSheet(
            selectedTab = "Tab 1",
            onDismissRequest = { showBottomSheet.value = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppMainContent(
    weatherViewModel: WeatherViewModel = hiltViewModel(),
    locationViewModel: LocationViewModel = hiltViewModel(),
    modifier: Modifier,
    showBottomSheet: MutableState<Boolean>,
    selectedCity: MutableState<String>,
    drawerState: CustomDrawerState,
    onDrawerClick: (CustomDrawerState) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val forecastState = weatherViewModel.forecastState.collectAsState()

    val snackBarHostState = remember { SnackbarHostState() }
    var location by remember { mutableStateOf<Location?>(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
          locationViewModel.getLocation(context)
        } else {
            coroutineScope.launch {
                snackBarHostState.showSnackbar(
                    "Permission is required for location"
                )
            }
        }
    }


    Scaffold(
        modifier = modifier.clickable(enabled = drawerState == CustomDrawerState.Opened) {
            onDrawerClick(CustomDrawerState.Closed)
        },
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
                                showBottomSheet.value = !showBottomSheet.value
                            },
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = selectedCity.value,
                                modifier = Modifier.padding(start = 4.dp),
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onDrawerClick(drawerState.opposite()) }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { /* chat icon click */ }) {
                        Icon(
                            Icons.Default.MailOutline,
                            contentDescription = "Chat",
                            tint = Color.White
                        )
                    }
                },
            )
        },
        containerColor = MyAppTheme.colorScheme.background,
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                fetchLocation(
                    context = context,
                    viewModel = locationViewModel,
                    permissionLauncher = permissionLauncher
                )
            }) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location", tint = Color.White)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF3A003B))
                .padding(innerPadding)
                .padding(vertical = 16.dp, horizontal = 24.dp)
        ) {

            when (val state = forecastState.value) {
                is WeatherUiState.Loading -> {
                    // Show loading indicator
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is WeatherUiState.Error -> {
                    // Show error message
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = state.message, color = Color.White)
                    }
                }

                is WeatherUiState.Success -> {
                    val forecast = state.data
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            CurrentWeatherCard(
                                city = selectedCity.value,
                                currentWeather = forecast.currentWeather
                            )
                        }
                        item {
                            WarningCard(warning = "Warrning")
                        }
                        item {
                            ForecastSection(forecastList = forecast.dailyWeather)
                        }
                        // Add more sections as needed
                    }
                }
            }
        }
    }

}

fun fetchLocation(
    context: Context,
    permissionLauncher: ManagedActivityResultLauncher<String, Boolean>,
    viewModel: LocationViewModel
) {
    val permission = Manifest.permission.ACCESS_COARSE_LOCATION
    when (ContextCompat.checkSelfPermission(context, permission)) {
        PackageManager.PERMISSION_GRANTED -> {
            viewModel.getLocation(context)
        }
        else -> {
            permissionLauncher.launch(permission)
        }
    }
}

@Composable
fun DrawerContent(viewModel: WeatherViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val currentLocale = remember { mutableStateOf("en") }

    val selectedLanguageState = viewModel.selectedLanguage.collectAsState()


    LaunchedEffect(selectedLanguageState.value) {
        currentLocale.value = selectedLanguageState.value
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, end = 80.dp)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Circular Icon with Border
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .border(4.dp, MyAppTheme.colorScheme.primary, CircleShape)
                    .clip(CircleShape)
                    .background(MyAppTheme.colorScheme.surface),
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
                style = MyAppTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Divider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MyAppTheme.colorScheme.onSurface.copy(alpha = 0.2f)
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
            color = MyAppTheme.colorScheme.onSurface.copy(alpha = 0.2f)
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
