package data.repository

import data.dto.Product
import data.dto.ProductResponse
import data.remote.AbstractKtorService
import domain.repository.IRepository

class Repository(private val ktorService: AbstractKtorService): IRepository {
    override suspend fun getAllProducts(): ProductResponse {
        return ktorService.getAllProducts()
    }

    override suspend fun getSingleProduct(prodId: Int): Product {
        return ktorService.getSingleProduct(prodId)
    }
}
