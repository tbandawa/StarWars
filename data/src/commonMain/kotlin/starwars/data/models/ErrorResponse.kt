package starwars.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val detail: String?
)
