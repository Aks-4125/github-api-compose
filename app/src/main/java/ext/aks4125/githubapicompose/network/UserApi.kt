package ext.aks4125.githubapicompose.network

import ext.aks4125.githubapicompose.network.model.UserApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id: String): UserApiModel

    @GET("/users/{id}/{type}")
    suspend fun getUserList(
        @Path("id") id: String,
        @Path("type") type: String
    ): List<UserApiModel>

}
