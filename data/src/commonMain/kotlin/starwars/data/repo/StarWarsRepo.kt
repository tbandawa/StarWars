package starwars.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import starwars.data.api.StarWarsApi
import starwars.data.models.ErrorResponse
import starwars.data.models.Film
import starwars.data.models.Person
import starwars.data.models.Planet
import starwars.data.models.RootResource
import starwars.data.models.Species
import starwars.data.models.Starship
import starwars.data.models.Vehicle
import starwars.data.state.ResourceResult

class StarWarsRepo(private val starWarsApi: StarWarsApi) {

    fun getPagedResources(resourceType: String): Flow<PagingData<Any>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = {
                ResourcesPagingSource(resourceType, starWarsApi)
            }
        ).flow.flowOn(Dispatchers.IO)
    }

    suspend fun getRootResources(): Flow<ResourceResult<RootResource>> = flow {
        emit(ResourceResult.Loading)
        emit(handleApiCall {
            starWarsApi.getRootResources()
        })
    }.flowOn(Dispatchers.IO)

    suspend fun getResources(resourceType: String, page: Int = 1): Flow<ResourceResult<Any>> = flow {
        emit(ResourceResult.Loading)
        emit(handleApiCall {
            when (resourceType) {
                "people" -> {
                    starWarsApi.getResources<Person>(resourceType, page)
                }
                "planets" -> {
                    starWarsApi.getResources<Planet>(resourceType, page)
                }
                "films" -> {
                    starWarsApi.getResources<Film>(resourceType, page)
                }
                "species" -> {
                    starWarsApi.getResources<Species>(resourceType, page)
                }
                "vehicles" -> {
                    starWarsApi.getResources<Vehicle>(resourceType, page)
                }
                "starships" -> {
                    starWarsApi.getResources<Starship>(resourceType, page)
                }
                else -> { ErrorResponse("Invalid resource type") }
            }
        })
    }.flowOn(Dispatchers.IO)

    suspend fun searchResources(resourceType: String, search: String, page: Int = 1): Flow<ResourceResult<Any>> = flow {
        emit(ResourceResult.Loading)
        emit(handleApiCall {
            when (resourceType) {
                "people" -> {
                    starWarsApi.searchResources<Person>(resourceType, search, page)
                }
                "planets" -> {
                    starWarsApi.searchResources<Planet>(resourceType, search, page)
                }
                "films" -> {
                    starWarsApi.searchResources<Film>(resourceType, search, page)
                }
                "species" -> {
                    starWarsApi.searchResources<Species>(resourceType, search, page)
                }
                "vehicles" -> {
                    starWarsApi.searchResources<Vehicle>(resourceType, search, page)
                }
                "starships" -> {
                    starWarsApi.searchResources<Starship>(resourceType, search, page)
                }
                else -> { ErrorResponse("Invalid resource type") }
            }
        })
    }.flowOn(Dispatchers.IO)

    suspend fun getResource(resourceType: String, resourceId: Int): Flow<ResourceResult<Any>> = flow {
        emit(ResourceResult.Loading)
        emit(handleApiCall {
            when (resourceType) {
                "people" -> {
                    starWarsApi.getResource<Person>(resourceType, resourceId)
                }
                "planets" -> {
                    starWarsApi.getResource<Planet>(resourceType, resourceId)
                }
                "films" -> {
                    starWarsApi.getResource<Film>(resourceType, resourceId)
                }
                "species" -> {
                    starWarsApi.getResource<Species>(resourceType, resourceId)
                }
                "vehicles" -> {
                    starWarsApi.getResource<Vehicle>(resourceType, resourceId)
                }
                "starships" -> {
                    starWarsApi.getResource<Starship>(resourceType, resourceId)
                }
                else -> { ErrorResponse("Invalid resource type") }
            }
        })
    }.flowOn(Dispatchers.IO)
}

suspend fun <T> handleApiCall(
    apiCall: suspend () -> T
): ResourceResult<T> {
    return try {
        val response = apiCall()
        ResourceResult.Success(response)
    } catch (exception: ResponseException) {
        when(exception) {
            is ClientRequestException -> {
                ResourceResult.Error(exception.response.body<ErrorResponse>())
            } else -> {
            ResourceResult.Error(ErrorResponse(exception.message))
        }
        }
    } catch (e: IOException) {
        ResourceResult.Error(ErrorResponse("Unable to connect to host"))
    } catch (e: Exception) {
        ResourceResult.Error(ErrorResponse("Unknown Error"))
    }
}