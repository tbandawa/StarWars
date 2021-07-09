package starwars.data.model

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
    val residents : List<String>,
    val films : List<String>,
    override val created : String,
    override val edited : String,
    override val url : String
) : BaseResource