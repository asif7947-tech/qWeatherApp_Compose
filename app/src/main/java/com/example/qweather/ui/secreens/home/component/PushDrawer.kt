package com.example.qweather.ui.secreens.home.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.qweather.ui.theme.MyAppTheme

@Composable
fun PushDrawer(
    drawerContent: @Composable () -> Unit,
    mainContent: @Composable (openDrawer: () -> Unit) -> Unit,
    drawerWidth: Dp = 280.dp
) {
    var drawerOpen by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Animate the offset of the main content
    val offsetX by animateDpAsState(
        targetValue = if (drawerOpen) drawerWidth else 0.dp,
        animationSpec = tween(durationMillis = 350)
    )

    // Animate the scrim
    val scrimAlpha by animateFloatAsState(
        targetValue = if (drawerOpen) 0.3f else 0f,
        animationSpec = tween(durationMillis = 350)
    )

    Box(Modifier.fillMaxSize()) {
        // Drawer (behind)
        Box(
            Modifier
                .width(drawerWidth)
                .fillMaxHeight()
                .background(MyAppTheme.colorScheme.surface)
        ) {
            drawerContent()
        }

        // Scrim (click to close)
        if (drawerOpen) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(MyAppTheme.colorScheme.onSurface.copy(alpha = scrimAlpha))
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { _, _ -> }
                    }
                    .pointerInput(Unit) {
                        detectTapGestures { drawerOpen = false }
                    }
            )
        }

        // Main content (slides right)
        Box(
            Modifier
                .offset(x = offsetX)
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            if (offsetX > drawerWidth / 2) drawerOpen = true else drawerOpen = false
                        },
                        onHorizontalDrag = { change, dragAmount ->
                            // Optionally: implement drag-to-open/close
                        }
                    )
                }
        ) {
            mainContent { drawerOpen = true }
        }
    }
}