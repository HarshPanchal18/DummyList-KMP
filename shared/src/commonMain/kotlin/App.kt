import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import domain.entity.ProductEntity
import presentation.screens.DefaultScreen
import presentation.screens.ErrorScreen
import presentation.screens.LoadingScreen
import presentation.screens.ProductListScreen
import presentation.viewmodel.ProductsViewModel

@Composable
fun ProductTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(primary = Color.Black),
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp)
        )
    ) {
        content()
    }
}

@Composable
fun App() {
    ProductTheme {
        val productsViewModel = getViewModel(Unit, viewModelFactory { ProductsViewModel() })
        ProductListPage(productsViewModel)
    }
}

@Composable
fun ProductListPage(viewModel: ProductsViewModel) {
    val uiState by viewModel.products.collectAsState()

    Column(
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
    }
}

expect fun getPlatformName(): String
