package com.example.qweather.ui.secreens.home.component

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.qweather.R
import com.example.qweather.ui.components.LanguageSelector
import com.example.qweather.ui.secreens.home.WeatherViewModel
import com.example.qweather.ui.theme.MyAppTheme


@Composable
fun CustomDrawer(
    viewModel: WeatherViewModel = hiltViewModel(),
    onCloseClick: () -> Unit
) {

    val context = LocalContext.current
    val currentLocale = remember { mutableStateOf("en") }

    val selectedLanguageState = viewModel.selectedLanguage.collectAsState()


    LaunchedEffect(selectedLanguageState.value) {
        currentLocale.value = selectedLanguageState.value
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction = 0.9f)
            .padding(horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
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

            Spacer(modifier = Modifier.height(16.dp))
    }

}