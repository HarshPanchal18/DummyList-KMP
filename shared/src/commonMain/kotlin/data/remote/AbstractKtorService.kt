package data.remote

import data.dto.Product
import data.dto.ProductResponse

interface AbstractKtorService {
    suspend fun getSingleProduct(id: Int): Product
    suspend fun getAllProducts(): ProductResponse
}
