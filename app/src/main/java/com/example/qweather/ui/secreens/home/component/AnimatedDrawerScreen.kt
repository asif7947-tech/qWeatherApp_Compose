import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedDrawerScreen(
    drawerContent: @Composable () -> Unit,
    mainContent: @Composable () -> Unit,
    drawerState: DrawerState
) {
    // Calculate progress: 0f (closed) to 1f (open)
// Calculate progress: 0f (closed) to 1f (open)
    val progress = if (drawerState.isOpen) 1f else if (drawerState.isClosed) 0f else drawerState.offset.value / drawerState.offset.value

    // Animate scale and corner radius
    val scale by animateFloatAsState(targetValue = 1f - 0.08f * progress, label = "scale")
    val corner by animateDpAsState(targetValue = (24 * progress).dp, label = "corner")

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = drawerContent
    ) {
        // Dim background when drawer is open
        Box(
            Modifier
                .fillMaxSize()
                .background(if (progress > 0f) Color.Black.copy(alpha = 0.2f * progress) else Color.Transparent)
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .scale(scale)
                    .clip(RoundedCornerShape(corner))
            ) {
                mainContent()
            }
        }
    }
}