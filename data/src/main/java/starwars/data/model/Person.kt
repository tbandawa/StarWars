package starwars.data.model

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
) : BaseResource