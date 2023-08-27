package data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpCallValidator
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorBuilder {

    fun createHttpClient(enableNetworkLogs: Boolean = true) = HttpClient {
        defaultRequest { // Set default request parameters
            header("Content-Type", "application/json")
            header("Accept", "application/json")
        }

        // Handling Exceptions
        install(HttpTimeout) {

            // Time period required to process an Http call: from sending a request to receiving the response
            requestTimeoutMillis = 10000

            // Time period in which a client should establish a connection with server
            connectTimeoutMillis = 10000

            // Maximum time of inactivity between 2 data packets when exchanging data
            socketTimeoutMillis = 10000
        }

        install(HttpRequestRetry) {
            maxRetries = 3 // Maximum tries to request
            // Retry logics for a failed response
            retryIf { httpRequest, httpResponse -> !httpResponse.status.isSuccess() }
            retryOnExceptionIf { httpRequestBuilder, cause -> cause is HttpRequestTimeoutException }
            delayMillis { 3000L } // retries on each 3 seconds (3,6,9,...)
        }

        install(HttpCallValidator) {
            handleResponseExceptionWithRequest { cause, request -> println("Exception: $cause") }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys =
                        true // Ignore Unknown keywords instead of throwing Exception
                    isLenient =
                        true // Quoted boolean literals, and unquoted string literals are allowed.
                    encodeDefaults =
                        true // Specifies whether default values of Kotlin properties should be encoded
                    prettyPrint = true // Format the json
                    coerceInputValues =
                        true // Enables coercing incorrect JSON values to the default property value
                }
            )
        }

        if (enableNetworkLogs) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }
}
