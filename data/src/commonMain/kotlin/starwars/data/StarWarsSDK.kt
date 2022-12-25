package starwars.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import starwars.data.api.BaseApiCall
import starwars.data.api.StarWarsApi
import starwars.data.models.BaseResource
import starwars.data.models.ResourceResult

class StarWarsSDK(private val starWarsApi: StarWarsApi): BaseApiCall() {

    suspend fun getRootResources(): Flow<ResourceResult<BaseResource>> = flow {
        emit(ResourceResult.Loading)
        emit(handleApiCall {
            starWarsApi.getBaseResources()
        })
    }.flowOn(Dispatchers.Default)

}