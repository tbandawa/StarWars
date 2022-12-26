package starwars.data.models

data class Planet (
    val name : String,
    val rotation_period : String,
    val orbital_period : String,
    val diameter : String,
    val climate : String,
    val gravity : String,
    val terrain : String,
    val surface_water : String,
    val population : String,
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
