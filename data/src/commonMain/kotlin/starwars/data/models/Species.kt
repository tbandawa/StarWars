package starwars.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Species (
    val name: String,
    val classification: String,
    val designation: String,
    val average_height: String,
    val skin_colors: String,
    val hair_colors: String,
    val eye_colors: String,
    val average_lifespan: String,
    val homeworld: String?,
    val language: String,
    val people: List<String>,
    val films: List<String>,
    val created: String,
    val edited: String,
    val url: String
)
