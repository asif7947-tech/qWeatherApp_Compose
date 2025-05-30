package com.example.qweather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.qweather.ui.secreens.home.HomeScreen
import com.example.qweather.ui.secreens.splash.SplashScreen


object AppScreensRoute{
    val SPLASH_SCREEN = "SplashScreen"
    val HOME_SCREEN = "HomeScreen"
}


@Composable
fun AppRootHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreensRoute.SPLASH_SCREEN
    )
    {
        composable(AppScreensRoute.SPLASH_SCREEN) {
            SplashScreen(navController)
        }
        composable(AppScreensRoute.HOME_SCREEN) {
            HomeScreen(navController)
        }

    }
}