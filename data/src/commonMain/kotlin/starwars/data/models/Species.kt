package starwars.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Species (
    val name : String,
    val classification : String,
    val designation : String,
    val average_height : String,
    val skin_colors : String,
    val hair_colors : String,
    val eye_colors : String,
    val average_lifespan : String,
    val homeworld : String,
    val language : String,
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
