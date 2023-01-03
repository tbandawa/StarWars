package starwars.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Film (
    val title : String,
    val episode_id : Int,
    val opening_crawl : String,
    val director : String,
    val producer : String,
    val release_date : String,
    override val characters : List<String>,
    override val planets : List<String>,
    override val starships : List<String>,
    override val vehicles : List<String>,
    override val species : List<String>,
    override val pilots: List<String>,
    override val films: List<String>,
    override val residents: List<String>,
    override val people: List<String>,
    override val created : String,
    override val edited : String,
    override val url : String
) : CommonResource
