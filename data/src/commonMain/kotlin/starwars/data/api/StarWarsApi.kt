package starwars.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import starwars.data.models.BaseResource
import starwars.data.models.Films
import starwars.data.models.People
import starwars.data.models.Planets
import starwars.data.models.Species
import starwars.data.models.Starships
import starwars.data.models.Vehicles

class StarWarsApi {

    companion object {
        const val baseUrl = "https://swapi.dev/api/"
        const val PAGE = "page"
    }

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

    suspend fun getRootResources(): BaseResource {
        return httpClient.get(baseUrl).body()
    }

    suspend fun getFilms(resourceType: String, page: Int): Films {
        return httpClient.get {
            url(baseUrl + resourceType)
            parameter(PAGE, page)
        }.body()
    }

    suspend fun getPeople(resourceType: String, page: Int): People {
        return httpClient.get{
            url(baseUrl + resourceType)
            parameter(PAGE, page)
        }.body()
    }

    suspend fun getPlanets(resourceType: String, page: Int): Planets {
        return httpClient.get{
            url(baseUrl + resourceType)
            parameter(PAGE, page)
        }.body()
    }

    suspend fun getVehicles(resourceType: String, page: Int): Vehicles {
        return httpClient.get{
            url(baseUrl + resourceType)
            parameter(PAGE, page)
        }.body()
    }

    suspend fun getStarships(resourceType: String, page: Int): Starships {
        return httpClient.get{
            url(baseUrl + resourceType)
            parameter(PAGE, page)
        }.body()
    }

    suspend fun getSpecies(resourceType: String, page: Int): Species {
        return httpClient.get{
            url(baseUrl + resourceType)
            parameter(PAGE, page)
        }.body()
    }
}