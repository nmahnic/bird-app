import androidx.compose.runtime.Composable
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import presentation.BirdAppTheme
import presentation.BirdsViewModel
import presentation.MainScreen

@Composable
fun App() {
    BirdAppTheme {
        val birdsViewModel = getViewModel(Unit, viewModelFactory { BirdsViewModel() })
        MainScreen(birdsViewModel)
    }
}


expect fun getPlatformName(): String