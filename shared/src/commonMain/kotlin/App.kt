import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.viewModelFactory
import presentation.viewmodel.ProductsViewModel

@Composable
fun ProductTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(colors = MaterialTheme.colors.copy(primary = Color.Black),
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
        //val productsViewModel = getViewModel(Unit, viewModelFactory { ProductsViewModel() })
        //ProductListPage(productsViewModel)
    }
}

@Composable
fun ProductListPage(viewModel: ProductsViewModel) {
    //val uiState by viewModel
}
