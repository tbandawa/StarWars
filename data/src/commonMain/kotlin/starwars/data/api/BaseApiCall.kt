package starwars.data.api

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.utils.io.errors.IOException
import starwars.data.models.ErrorResponse
import starwars.data.state.ResourceResult

abstract class BaseApiCall {

    suspend fun <T> handleApiCall(
        apiCall: suspend () -> T
    ): ResourceResult<T> {
        return try {
            val response = apiCall()
            ResourceResult.Success(response)
        } catch (exception: ResponseException) {
            println(exception.message)
            when(exception) {
                is ClientRequestException -> {
                    ResourceResult.Error(exception.response.body<ErrorResponse>())
                } else -> {
                    ResourceResult.Error(ErrorResponse(exception.message))
                }
            }
        } catch (e: IOException) {
            ResourceResult.Error(ErrorResponse("Unable to connect to host"))
        } catch (e: Exception) {
            println(e.message)
            ResourceResult.Error(ErrorResponse("Unknown Error"))
        }
    }

}