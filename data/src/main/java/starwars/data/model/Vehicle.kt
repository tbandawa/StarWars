package starwars.data.model

data class Vehicle (
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
    val vehicle_class : String,
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