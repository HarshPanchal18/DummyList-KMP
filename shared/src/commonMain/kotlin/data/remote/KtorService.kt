package data.remote

import data.dto.Product
import data.dto.ProductResponse
import data.getUrl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorService(private val httpClient: HttpClient) : AbstractKtorService {
    override suspend fun getSingleProduct(id: Int): Product {
        return httpClient.get(EndPoints.GET_SINGLE_PRODUCT.getUrl(id)).body()
    }

    override suspend fun getAllProducts(): ProductResponse {
        return httpClient.get(EndPoints.GET_ALL_PRODUCTS).body()
    }
}
