package starwars.data.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import starwars.data.models.BaseResource
import starwars.data.models.BaseResult

class StarWarsApi {

    private val httpClient = HttpClient {
        expectSuccess = true
        install(HttpTimeout) {
            requestTimeoutMillis = 15000L
            connectTimeoutMillis = 15000L
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getBaseResources(): BaseResource {
        return httpClient.get(baseUrl).body()
    }

    suspend fun <T>getBaseResource(): BaseResult<T> {
        return httpClient.get(baseUrl).body()
    }

    companion object {
        private const val baseUrl = "https://swapi.dev/api/"
    }
}