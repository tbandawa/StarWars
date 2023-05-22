package starwars.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Film (
    val title: String,
    val episode_id: Long,
    val opening_crawl: String,
    val director: String,
    val producer: String,
    val release_date: String,
    val characters: List<String>,
    val planets: List<String>,
    val starships: List<String>,
    val vehicles: List<String>,
    val species: List<String>,
    val created: String,
    val edited: String,
    val url: String
)
