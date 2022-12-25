package starwars.data.models

sealed class ResourceResult<out M> {
    data class Success<out M>(val data: M): ResourceResult<M>()
    data class Error<out M>(val data: ErrorResponse? = null) : ResourceResult<M>()
    object Loading : ResourceResult<Nothing>()
    object Empty : ResourceResult<Nothing>()
}
