package com.example.qweather

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.qweather.navigation.AppRootHost
import com.example.qweather.ui.secreens.home.WeatherViewModel
import com.example.qweather.ui.theme.MyAppTheme
import com.example.qweather.ui.theme.QWeatherAppTheme
import com.example.qweather.utils.LocaleUtils
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        // Set the status bar and navigation bar colors to transparent
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // Set the default night mode to not night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        enableEdgeToEdge()
        // Install the Boot Splash Screen
        installSplashScreen()
        setContent {
            val viewModel: WeatherViewModel = hiltViewModel()
            val currentLanguage by viewModel.selectedLanguage.collectAsState()
            
            // Set the locale based on the saved language preference
            LocaleUtils.setLocale(this, currentLanguage)
            
            QWeatherAppTheme {
                val navController = rememberNavController()
                val layoutDirection = LocaleUtils.getLayoutDirection(currentLanguage)
                
                CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MyAppTheme.colorScheme.background),
                    ) {
                        AppRootHost(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QWeatherAppTheme {
        Greeting("Android")
    }
}