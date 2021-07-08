package starwars.data.model

data class Species (
    val name : String,
    val classification : String,
    val designation : String,
    val average_height : Int,
    val skin_colors : String,
    val hair_colors : String,
    val eye_colors : String,
    val average_lifespan : Int,
    val homeworld : String,
    val language : String,
    val people : List<String>,
    val films : List<String>,
    override val created : String,
    override val edited : String,
    override val url : String
) : BaseResource