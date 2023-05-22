package starwars.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Species(
    val count: Long,
    val next: String?,
    val previous: String?,
    val results: List<Specie>
)
