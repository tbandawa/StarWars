package starwars.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import starwars.data.model.BaseResult
import starwars.data.model.Root

interface SwApi {

    @GET("api/")
    suspend fun fetchRootData() : Root

    @GET("api/{resourceType}/")
    suspend fun fetchResources(@Path("resourceType") type: String) : BaseResult<Any>

    @GET("api/{resourceType}/{resourceId}/")
    suspend fun fetchResource(@Path("resourceType") type: String, @Path("resourceId") id: Int) : Any

    @GET("api/{resourceType}/")
    suspend fun searchResource(@Path("resourceType") type: String, @Query("search") query: String) : BaseResult<Any>

}