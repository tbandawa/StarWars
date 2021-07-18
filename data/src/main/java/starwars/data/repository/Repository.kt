package starwars.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import starwars.data.api.SwApi
import starwars.data.api.response.Resource
import starwars.data.model.*
import starwars.data.util.ContextProviders
import starwars.data.util.ResourcesParser
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.full.memberProperties

@Singleton
class Repository @Inject constructor(
    private val swApi: SwApi,
    private val contextProviders: ContextProviders
) {

    suspend fun getRootData() : Flow<Resource<List<Item>>> = flow {
            try {
                emit(Resource.loading(null))
                val response = swApi.fetchRootData()
                val data = if (response.isSuccessful) {
                    response.body()?.let { root ->
                        val resources = mutableListOf<Item>()
                        for (prop in Root::class.memberProperties) {
                            resources.add(Item(prop.name, "${prop.get(root)}"))
                        }
                        resources
                    } ?: run {
                        emptyList()
                    }
                } else {
                    emptyList()
                }
                emit(Resource.success(data))
            } catch (exception: Exception) {
                emit(Resource.error(exception.message ?: "Error Occurred!", null))
            }
        }.flowOn(contextProviders.IO)

    suspend fun getResources(resourceType: String): Resource<BaseResult<Any>> {
        return try {
            val response = swApi.fetchResources(resourceType)
            val data = if (response.isSuccessful) {
                response.body()?.let { baseResult ->
                    ResourcesParser.parseToBaseResult(resourceType, baseResult)
                } ?: run {
                    null
                }
            } else {
                null
            }
            Resource.success(data)
        } catch (exception: Exception) {
            Resource.error(exception.message ?: "Error Occurred!", null)
        }
    }

    suspend fun getResourcesByPage(resourceType: String, page: Int): Resource<BaseResult<Any>> {
        return try {
            val response = swApi.fetchResourcesByPage(resourceType, page)
            val data = if (response.isSuccessful) {
                response.body()?.let { baseResult ->
                    ResourcesParser.parseToBaseResult(resourceType, baseResult)
                } ?: run {
                    null
                }
            } else {
                null
            }
            Resource.success(data)
        } catch (exception: Exception) {
            Resource.error(exception.message ?: "Error Occurred!", null)
        }
    }

    suspend fun getResource(resourceType: String, resourceId: Int) : Resource<Any> {
        return try {
            val response = swApi.fetchResource(resourceType, resourceId)
            val data = if (response.isSuccessful) {
                response.body()?.let { resource ->
                    ResourcesParser.parseToBaseResource(resourceType, resource)
                } ?: run {
                    null
                }
            } else {
                null
            }
            Resource.success(data)
        } catch (exception: Exception) {
            Resource.error(exception.message ?: "Error Occurred!", null)
        }
    }

    suspend fun searchResource(resourceType: String, searchQuery: String): Resource<BaseResult<Any>> {
        return try {
            val response = swApi.searchResource(resourceType, searchQuery)
            val data = if (response.isSuccessful) {
                response.body()?.let { baseResult ->
                    ResourcesParser.parseToBaseResult(resourceType, baseResult)
                } ?: run {
                    null
                }
            } else {
                null
            }
            Resource.success(data)
        } catch (exception: Exception) {
            Resource.error(exception.message ?: "Error Occurred!", null)
        }
    }

}