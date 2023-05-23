package starwars.data.models

import kotlinx.serialization.Serializable

@Serializable
data class RootResource(
    val films: String?,
    val people: String?,
    val planets: String?,
    val species: String?,
    val starships: String?,
    val vehicles: String?
)

operator fun RootResource.iterator(): List<Pair<String, String?>> {
    return listOf(
        "films" to films,
        "people" to people,
        "planets" to planets,
        "species" to species,
        "starships" to starships,
        "vehicles" to vehicles
    )
}
