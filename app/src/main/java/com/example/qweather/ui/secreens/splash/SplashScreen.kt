package com.example.qweather.ui.secreens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import kotlinx.coroutines.delay
import com.example.qweather.R
import com.example.qweather.navigation.AppScreensRoute
import com.example.qweather.ui.theme.MyAppTheme

/**
 * Created by Asif Mehmmood on 2024-11-02.
 * This is Splash Screen of the application
 *
 */

@Composable
fun SplashScreen(navController: NavHostController) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MyAppTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {

        LaunchedEffect(key1 = true) {
            // delay for 3 seconds
            delay(2000)
            navController.navigate(route = AppScreensRoute.HOME_SCREEN,
                navOptions = NavOptions.Builder().setPopUpTo(AppScreensRoute.SPLASH_SCREEN, true)
                    .build()
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.width(200.dp).height(100.dp),
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = context.getString(R.string.app_name),
                contentScale = ContentScale.Crop,
            )
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = context.getString(R.string.app_name),
                    color = MyAppTheme.colorScheme.text,
                    style = MyAppTheme.typography.appMainHeading
                )
            }
        }
    }
}