package starwars.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import starwars.data.models.BaseResource

class StarWarsApi {

    private val httpClient = HttpClient {
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

    companion object {
        private const val baseUrl = "https://swapi.dev/api/"
    }
}