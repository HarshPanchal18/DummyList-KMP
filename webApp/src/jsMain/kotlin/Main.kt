import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import domain.entity.ProductEntity
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable
import org.jetbrains.skia.skottie.Logger
import presentation.screens.DefaultScreen
import presentation.screens.ErrorScreen
import presentation.screens.LoadingScreen
import presentation.screens.ProductListScreen
import presentation.viewmodel.ProductsViewModel

fun main() {
    renderComposable(rootElementId = "root") {
        webView()
    }
}

@Composable
fun webView() {
    val productsViewModel = getViewModel(Unit, viewModelFactory { ProductsViewModel() })
    //ProductListPage(productsViewModel)
    val uiState by productsViewModel.products.collectAsState()

    /*Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            is ProductsViewModel.ProductState.Error ->
                ErrorScreen((uiState as ProductsViewModel.ProductState.Error<List<ProductEntity>>).message.message)

            is ProductsViewModel.ProductState.Idle -> DefaultScreen()

            is ProductsViewModel.ProductState.Loading -> LoadingScreen()

            is ProductsViewModel.ProductState.Success ->
                (uiState as ProductsViewModel.ProductState.Success<List<ProductEntity>>).data.apply {
                    ProductListScreen(this)
                }
        }
    }*/

    Div({ style { padding(1.em) } }) {
        H1{ Text("Hello, ${getPlatformName()}!")}

        Div {
            when (uiState) {
                is ProductsViewModel.ProductState.Error ->
                    ErrorScreen((uiState as ProductsViewModel.ProductState.Error<List<ProductEntity>>).message.message)

                is ProductsViewModel.ProductState.Idle -> DefaultScreen()

                is ProductsViewModel.ProductState.Loading -> LoadingScreen()

                is ProductsViewModel.ProductState.Success ->
                    (uiState as ProductsViewModel.ProductState.Success<List<ProductEntity>>).data.apply {
                        ProductListScreen(this)
                    }
            }
        }
    }
}
