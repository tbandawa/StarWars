package starwars.data.models

import kotlinx.serialization.Serializable

@Serializable
data class BaseResource<T> (
    val count : Long,
    val next : String?,
    val previous : String?,
    var results : List<T>
)
