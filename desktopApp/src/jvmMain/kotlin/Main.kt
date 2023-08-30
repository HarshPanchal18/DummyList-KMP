import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import presentation.viewmodel.ProductsViewModel

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Latest Mobiles") {
        MaterialTheme { DeskView() }
    }
}

@Composable
fun DeskView() {
    val productsViewModel = getViewModel(Unit, viewModelFactory { ProductsViewModel() })
    ProductListPage(productsViewModel)
}
