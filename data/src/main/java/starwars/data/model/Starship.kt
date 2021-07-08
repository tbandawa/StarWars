package starwars.data.model

data class Starship (
    val name : String,
    val model : String,
    val manufacturer : String,
    val cost_in_credits : Int,
    val length : Int,
    val max_atmosphering_speed : Int,
    val crew : String,
    val passengers : Int,
    val cargo_capacity : Int,
    val consumables : String,
    val hyperdrive_rating : Double,
    val mGLT : Int,
    val starship_class : String,
    val pilots : List<String>,
    val films : List<String>,
    override val created : String,
    override val edited : String,
    override val url : String
) : BaseResource