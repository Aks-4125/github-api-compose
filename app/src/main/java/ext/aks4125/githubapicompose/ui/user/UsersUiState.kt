package ext.aks4125.githubapicompose.ui.user

import ext.aks4125.githubapicompose.network.model.UserApiModel

data class UsersUiState(
    val user: UserApiModel? = null,
    val notFound: Boolean = false,
)