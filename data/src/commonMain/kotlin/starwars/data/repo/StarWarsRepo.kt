package starwars.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import starwars.data.api.BaseApiCall
import starwars.data.api.StarWarsApi
import starwars.data.models.RootResource
import starwars.data.models.ErrorResponse
import starwars.data.models.Film
import starwars.data.models.Person
import starwars.data.models.Planet
import starwars.data.models.Species
import starwars.data.models.Starship
import starwars.data.models.Vehicle
import starwars.data.state.ResourceResult

class StarWarsRepo(private val starWarsApi: StarWarsApi): BaseApiCall() {

    suspend fun getRootResources(): Flow<ResourceResult<RootResource>> = flow {
        emit(ResourceResult.Loading)
        emit(handleApiCall {
            starWarsApi.getRootResources()
        })
    }.flowOn(Dispatchers.Default)

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
    }.flowOn(Dispatchers.Default)

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
    }.flowOn(Dispatchers.Default)
}