package com.example.qweather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.qweather.ui.secreens.home.HomeScreen
import com.example.qweather.ui.secreens.splash.SplashScreen

@Composable
fun HomeRootHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreensRoute.HOME_SCREEN
    )
    {
        composable(AppScreensRoute.HOME_SCREEN) {
            HomeScreen(navController)
        }

    }
}