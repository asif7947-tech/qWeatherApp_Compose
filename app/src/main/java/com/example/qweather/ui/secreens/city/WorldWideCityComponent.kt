import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.qweather.ui.secreens.home.WeatherViewModel

@Composable
fun WorldWideCityComponent(weatherViewModel: WeatherViewModel) {
    val allWorldWideCities by weatherViewModel.worldWideCities.collectAsState()
    val context: Context = LocalContext.current

}