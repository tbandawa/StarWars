package starwars.data.models

import kotlinx.serialization.Serializable

@Serializable
data class BaseResource(
    val films: String?,
    val people: String?,
    val planets: String?,
    val species: String?,
    val starships: String?,
    val vehicles: String?
)