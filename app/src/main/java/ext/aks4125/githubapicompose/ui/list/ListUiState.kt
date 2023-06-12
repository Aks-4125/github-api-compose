package ext.aks4125.githubapicompose.ui.list

import ext.aks4125.githubapicompose.network.model.UserApiModel

data class ListUiState(
    val list: List<UserApiModel> = listOf(),
    val notFound: Boolean = false,
)