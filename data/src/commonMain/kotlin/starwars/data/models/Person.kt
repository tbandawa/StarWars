package starwars.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Person (
    val name : String,
    val height : String,
    val mass : String,
    val hair_color : String,
    val skin_color : String,
    val eye_color : String,
    val birth_year : String,
    val gender : String,
    val homeworld : String,
    val characters : List<String>,
    val planets : List<String>,
    val starships : List<String>,
    val vehicles : List<String>,
    val species : List<String>,
    val pilots: List<String>,
    val films: List<String>,
    val residents: List<String>,
    val people: List<String>,
    val created : String,
    val edited : String,
    val url : String
)
