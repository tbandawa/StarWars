package starwars.data.util

import com.google.gson.Gson
import starwars.data.model.*

interface ResourcesParser {
    companion object {

        fun parseToBaseResource(resourceType: String, any: Any) : BaseResource {
            return when (resourceType) {
                "people" -> {
                    Gson().fromJson(Gson().toJson(any), Person::class.java)
                }
                "planets" -> {
                    Gson().fromJson(Gson().toJson(any), Planet::class.java)
                }
                "films" -> {
                    Gson().fromJson(Gson().toJson(any), Film::class.java)
                }
                "species" -> {
                    Gson().fromJson(Gson().toJson(any), Species::class.java)
                }
                "vehicles" -> {
                    Gson().fromJson(Gson().toJson(any), Vehicle::class.java)
                }
                "starships" -> {
                    Gson().fromJson(Gson().toJson(any), Starship::class.java)
                }
                else -> throw Exception("Can not parse $any.")
            }
        }

        fun parseToBaseResult(resourceType: String, baseResult: BaseResult<Any>) : BaseResult<Any> {
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
                else -> throw Exception("Can not parse $baseResult.")
            }
            baseResult.results = resourcesList.toList()
            return baseResult
        }

    }
}