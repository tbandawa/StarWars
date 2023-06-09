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
import starwars.data.models.RootResource
import starwars.data.models.BaseResource

class StarWarsApi {

    companion object {
        const val baseUrl = "https://swapi.dev/api/"
        const val SEARCH = "search"
        const val PAGE = "page"
    }

    val httpClient = HttpClient {
        expectSuccess = true
        install(HttpTimeout) {
            requestTimeoutMillis = 15000L
            connectTimeoutMillis = 15000L
        }
        /*install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }*/
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getRootResources(): RootResource {
        return httpClient.get(baseUrl).body()
    }

    suspend inline fun <reified R> getResources(resourceType: String, page: Int): BaseResource<R> {
        return httpClient.get {
            url(baseUrl + resourceType)
            parameter(PAGE, page)
        }.body()
    }

    suspend inline fun <reified R> searchResources(resourceType: String, search: String, page: Int): BaseResource<R> {
        return httpClient.get {
            url(baseUrl + resourceType)
            parameter(SEARCH, search)
            parameter(PAGE, page)
        }.body()
    }

    suspend inline fun <reified R> getResource(resourceType: String, resourceId: Int): R {
        return httpClient.get {
            url("$baseUrl$resourceType/$resourceId")
        }.body()
    }
}