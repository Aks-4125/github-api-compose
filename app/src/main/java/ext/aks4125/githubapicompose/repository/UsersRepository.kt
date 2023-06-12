package ext.aks4125.githubapicompose.repository

import ext.aks4125.githubapicompose.network.UserApi
import ext.aks4125.githubapicompose.network.model.UserApiModel
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersApi: UserApi
) {
    suspend fun fetchUser(userId: String): UserApiModel {
        return usersApi.getUser(userId)
    }

    suspend fun fetchList(username: String, listType: String): List<UserApiModel> {
        return usersApi.getUserList(username, listType)
    }
}
