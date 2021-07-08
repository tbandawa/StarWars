package starwars.data.model

data class BaseResult<T> (
    val count : Int,
    val next : String,
    val previous : String,
    var results : List<T>
)