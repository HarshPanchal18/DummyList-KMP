package presentation.viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import di.DiModule
import domain.entity.ProductEntity
import domain.usecase.GetAllProdUseCase
import domain.usecase.GetSingleProdUseCase
import domain.utils.CustomMessage
import domain.utils.Result
import domain.utils.asResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val getAllProdUseCase: GetAllProdUseCase = DiModule.getAllProdUseCase,
    private val getSingleProdUseCase: GetSingleProdUseCase = DiModule.getSingleProdUseCase,
) : ViewModel() {

    private val _products = MutableStateFlow<ProductState<List<ProductEntity>>>(ProductState.Idle())
    val products = _products.asStateFlow()

    private val _product = MutableStateFlow<ProductState<ProductEntity>>(ProductState.Idle())
    val product = _product.asStateFlow()

    init {
        setIntent(ProductIntent.GetAllProduct)
    }

    fun setIntent(intent: ProductIntent) {
        when (intent) {
            is ProductIntent.GetAllProduct -> fetchAllProducts()
            is ProductIntent.GetSingleProduct -> fetchSingleProduct(intent.id)
        }
    }

    private fun fetchSingleProduct(id: Int) {
        viewModelScope.launch {
            val productApi = getSingleProdUseCase(id).asResult().collectLatest { result ->
                when (result) {
                    is Result.Loading -> _product.update { ProductState.Loading() }
                    is Result.Success -> _product.update { ProductState.Success(result.data) }
                    is Result.Error -> _product.update { ProductState.Error(result.message) }
                    is Result.Idle -> _product.update { ProductState.Idle() }
                }
            }
        }
    }

    private fun fetchAllProducts() {
        viewModelScope.launch {
            getAllProdUseCase().asResult().collectLatest { result ->
                when (result) {
                    is Result.Loading -> _products.update { ProductState.Loading() }
                    is Result.Success -> _products.update { ProductState.Success(result.data) }
                    is Result.Error -> _products.update { ProductState.Error(result.message) }
                    is Result.Idle -> _products.update { ProductState.Idle() }
                }
            }
        }
    }

    sealed interface ProductState<Entity> {
        class Loading<Entity> : ProductState<Entity>
        class Idle<Entity> : ProductState<Entity>
        data class Success<Entity>(val data: Entity) : ProductState<Entity>
        data class Error<Entity>(val message: CustomMessage) : ProductState<Entity>
    }

    sealed class ProductIntent {
        object GetAllProduct : ProductIntent()
        data class GetSingleProduct(val id: Int) : ProductIntent()
    }
}
