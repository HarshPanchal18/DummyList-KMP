package di

import data.remote.KtorBuilder.createHttpClient
import data.remote.KtorService
import data.repository.Repository
import domain.usecase.GetAllProdUseCase
import domain.usecase.GetSingleProdUseCase

object DiModule {
    private val httpClient = createHttpClient()
    private val service = KtorService(httpClient)
    private val repository = Repository(service)
    val getAllProdUseCase = GetAllProdUseCase(repository)
    val getSingleProdUseCase = GetSingleProdUseCase(repository)
}
