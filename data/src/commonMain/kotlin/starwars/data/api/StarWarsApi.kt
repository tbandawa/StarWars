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
import starwars.data.models.Film
import starwars.data.models.Films
import starwars.data.models.People
import starwars.data.models.Planets
import starwars.data.models.Species
import starwars.data.models.Starships
import starwars.data.models.Vehicles

class StarWarsApi {

    companion object {
        const val baseUrl = "https://swapi.dev/api/"
    }

    val httpClient = HttpClient {
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

    suspend fun getRootResources(): BaseResource {
        return httpClient.get(baseUrl).body()
    }

    suspend fun getFilms(resourceType: String): Films {
        return httpClient.get(baseUrl + resourceType).body()
    }

    suspend fun getPeople(resourceType: String): People {
        return httpClient.get(baseUrl + resourceType).body()
    }

    suspend fun getPlanets(resourceType: String): Planets {
        return httpClient.get(baseUrl + resourceType).body()
    }

    suspend fun getVehicles(resourceType: String): Vehicles {
        return httpClient.get(baseUrl + resourceType).body()
    }

    suspend fun getStarships(resourceType: String): Starships {
        return httpClient.get(baseUrl + resourceType).body()
    }

    suspend fun getSpecies(resourceType: String): Species {
        return httpClient.get(baseUrl + resourceType).body()
    }

}