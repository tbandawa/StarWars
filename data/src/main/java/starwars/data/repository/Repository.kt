package starwars.data.repository

import androidx.lifecycle.liveData
import com.google.gson.Gson
import starwars.data.api.SwApi
import starwars.data.api.response.Resource
import starwars.data.model.*
import starwars.data.util.ContextProviders
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val swApi: SwApi,
    private val contextProviders: ContextProviders
) {

    fun getRootData() = liveData(contextProviders.IO) {
        emit(Resource.loading(null))
        try {
            val response = swApi.fetchRootData()
            emit(Resource.success(response))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", null))
        }
    }

    fun getResources(resourceType: String) = liveData(contextProviders.IO) {
        emit(Resource.loading(null))
        try {

            val response = swApi.fetchResources(resourceType)

            val resourcesList = when (resourceType) {
                "people" -> {
                    Gson().fromJson(Gson().toJson(response.results), Array<Person>::class.java)
                }
                "planets" -> {
                    Gson().fromJson(Gson().toJson(response.results), Array<Planet>::class.java)
                }
                "films" -> {
                    Gson().fromJson(Gson().toJson(response.results), Array<Film>::class.java)
                }
                "species" -> {
                    Gson().fromJson(Gson().toJson(response.results), Array<Species>::class.java)
                }
                "vehicles" -> {
                    Gson().fromJson(Gson().toJson(response.results), Array<Vehicle>::class.java)
                }
                "starships" -> {
                    Gson().fromJson(Gson().toJson(response.results), Array<Starship>::class.java)
                }
                else -> throw Exception("Can not parse $response.")
            }

            response.results = resourcesList.toList()
            emit(Resource.success(response))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", null))
        }
    }

    fun getResource(resourceType: String, resourceId: Int) = liveData(contextProviders.IO) {
        emit(Resource.loading(null))
        try {

            val response = swApi.fetchResource(resourceType, resourceId)

            val resource = when (resourceType) {
                "people" -> {
                    Gson().fromJson(Gson().toJson(response), Person::class.java)
                }
                "planets" -> {
                    Gson().fromJson(Gson().toJson(response), Planet::class.java)
                }
                "films" -> {
                    Gson().fromJson(Gson().toJson(response), Film::class.java)
                }
                "species" -> {
                    Gson().fromJson(Gson().toJson(response), Species::class.java)
                }
                "vehicles" -> {
                    Gson().fromJson(Gson().toJson(response), Vehicle::class.java)
                }
                "starships" -> {
                    Gson().fromJson(Gson().toJson(response), Starship::class.java)
                }
                else -> throw Exception("Can not parse $response.")
            }

            emit(Resource.success(resource))
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", null))
        }
    }

    fun searchResource(resourceType: String, searchQuery: String) = liveData(contextProviders.IO) {

        Timber.d("searching $resourceType for $searchQuery")

        emit(Resource.loading(null))
        try {

            val response = swApi.searchResource(resourceType, searchQuery)

            Timber.d("response = $response")

            val resourcesList = when (resourceType) {
                "people" -> {
                    Gson().fromJson(Gson().toJson(response.results), Array<Person>::class.java)
                }
                "planets" -> {
                    Gson().fromJson(Gson().toJson(response.results), Array<Planet>::class.java)
                }
                "films" -> {
                    Gson().fromJson(Gson().toJson(response.results), Array<Film>::class.java)
                }
                "species" -> {
                    Gson().fromJson(Gson().toJson(response.results), Array<Species>::class.java)
                }
                "vehicles" -> {
                    Gson().fromJson(Gson().toJson(response.results), Array<Vehicle>::class.java)
                }
                "starships" -> {
                    Gson().fromJson(Gson().toJson(response.results), Array<Starship>::class.java)
                }
                else -> throw Exception("Can not parse $response.")
            }

            response.results = resourcesList.toList()
            emit(Resource.success(response))
        } catch (exception: Exception) {
            Timber.d("error = ${exception.message}")
            emit(Resource.error(exception.message ?: "Error Occurred!", null))
        }
    }

}