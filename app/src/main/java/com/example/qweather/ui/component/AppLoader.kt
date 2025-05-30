package com.example.qweather.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qweather.ui.theme.MyAppTheme

/**
 * Created by Asif Mehmmood on  2025-05-29
 * This is Loader of the App
 *  @return AppLoader Composable
 *
 */

@Composable
fun AppLoader() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp).align(Alignment.Center),
            color = MyAppTheme.colorScheme.tabIndicatorBgColor,
            strokeWidth = 4.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppLoaderPreview() {
}