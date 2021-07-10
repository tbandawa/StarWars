package starwars.data.repository

import com.google.gson.Gson
import starwars.data.api.SwApi
import starwars.data.api.response.Resource
import starwars.data.model.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.full.memberProperties

@Singleton
class Repository @Inject constructor(
    private val swApi: SwApi
) {

    suspend fun getRootData(): Resource<List<Item>> {
        return try {
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
            Resource.success(data)
        } catch (exception: Exception) {
            Resource.error(exception.message ?: "Error Occurred!", null)
        }
    }

    suspend fun getResources(resourceType: String): Resource<BaseResult<Any>> {
        return try {
            val response = swApi.fetchResources(resourceType)
            val data = if (response.isSuccessful) {
                response.body()?.let { baseResult ->
                    val resourcesList = when (resourceType) {
                        "people" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Person>::class.java)
                        }
                        "planets" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Planet>::class.java)
                        }
                        "films" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Film>::class.java)
                        }
                        "species" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Species>::class.java)
                        }
                        "vehicles" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Vehicle>::class.java)
                        }
                        "starships" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Starship>::class.java)
                        }
                        else -> throw Exception("Can not parse $response.")
                    }
                    baseResult.results = resourcesList.toList()
                    baseResult
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
                    val resourcesList = when (resourceType) {
                        "people" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Person>::class.java)
                        }
                        "planets" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Planet>::class.java)
                        }
                        "films" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Film>::class.java)
                        }
                        "species" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Species>::class.java)
                        }
                        "vehicles" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Vehicle>::class.java)
                        }
                        "starships" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Starship>::class.java)
                        }
                        else -> throw Exception("Can not parse $response.")
                    }
                    baseResult.results = resourcesList.toList()
                    baseResult
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
                    val baseResource = when (resourceType) {
                        "people" -> {
                            Gson().fromJson(Gson().toJson(resource), Person::class.java)
                        }
                        "planets" -> {
                            Gson().fromJson(Gson().toJson(resource), Planet::class.java)
                        }
                        "films" -> {
                            Gson().fromJson(Gson().toJson(resource), Film::class.java)
                        }
                        "species" -> {
                            Gson().fromJson(Gson().toJson(resource), Species::class.java)
                        }
                        "vehicles" -> {
                            Gson().fromJson(Gson().toJson(resource), Vehicle::class.java)
                        }
                        "starships" -> {
                            Gson().fromJson(Gson().toJson(resource), Starship::class.java)
                        }
                        else -> throw Exception("Can not parse $response.")
                    }
                    baseResource
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
                    val resourcesList = when (resourceType) {
                        "people" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Person>::class.java)
                        }
                        "planets" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Planet>::class.java)
                        }
                        "films" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Film>::class.java)
                        }
                        "species" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Species>::class.java)
                        }
                        "vehicles" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Vehicle>::class.java)
                        }
                        "starships" -> {
                            Gson().fromJson(Gson().toJson(baseResult.results), Array<Starship>::class.java)
                        }
                        else -> throw Exception("Can not parse $response.")
                    }
                    baseResult.results = resourcesList.toList()
                    baseResult
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