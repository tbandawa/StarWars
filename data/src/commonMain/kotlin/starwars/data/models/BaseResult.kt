package starwars.data.models

import kotlinx.serialization.Serializable

@Serializable
data class BaseResult<T> (
    val count : Int,
    val next : String,
    val previous : String,
    var results : List<T>
)
