package starwars.data.model

data class Starship (
    val name : String,
    val model : String,
    val manufacturer : String,
    val cost_in_credits : String,
    val length : String,
    val max_atmosphering_speed : String,
    val crew : String,
    val passengers : String,
    val cargo_capacity : String,
    val consumables : String,
    val hyperdrive_rating : String,
    val mGLT : String,
    val starship_class : String,
    val pilots : List<String>,
    val films : List<String>,
    override val created : String,
    override val edited : String,
    override val url : String
) : BaseResource