package domain.usecase

import data.dto.toSingleProductEntity
import domain.repository.IRepository
import kotlinx.coroutines.flow.flow

class GetSingleProdUseCase(private val repository: IRepository) {
    operator fun invoke(prodId: Int) = flow {
        val response = repository.getSingleProduct(prodId).toSingleProductEntity()
        emit(response)
    }
}
