package starwars.data.model

data class Vehicle (
    val name : String,
    val model : String,
    val manufacturer : String,
    val cost_in_credits : Int,
    val length : String,
    val max_atmosphering_speed : Int,
    val crew : Int,
    val passengers : Int,
    val cargo_capacity : Int,
    val consumables : String,
    val vehicle_class : String,
    val pilots : List<String>,
    val films : List<String>,
    override val created : String,
    override val edited : String,
    override val url : String
) : BaseResource