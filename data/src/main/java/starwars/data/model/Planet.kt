package starwars.data.model

data class Planet (
    val name : String,
    val rotation_period : Int,
    val orbital_period : Int,
    val diameter : Int,
    val climate : String,
    val gravity : String,
    val terrain : String,
    val surface_water : Int,
    val population : Int,
    val residents : List<String>,
    val films : List<String>,
    override val created : String,
    override val edited : String,
    override val url : String
) : BaseResource