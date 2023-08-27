package data.remote

object EndPoints {
    private const val BASE_URL = "https://dummyjson.com"
    const val GET_ALL_PRODUCTS = "$BASE_URL/products"
    const val GET_SINGLE_PRODUCT = "$BASE_URL/products/{prodId}"
}
