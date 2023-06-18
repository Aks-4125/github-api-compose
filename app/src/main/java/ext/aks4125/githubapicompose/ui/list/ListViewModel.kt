package ext.aks4125.githubapicompose.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ext.aks4125.githubapicompose.Argument
import ext.aks4125.githubapicompose.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf(ListUiState())
        private set

    private val username: String? = savedStateHandle[Argument.USERNAME]
    private val shouldLoadFollowing: Boolean? = savedStateHandle[Argument.FOLLOWING]


    init {
        loadList(username, shouldLoadFollowing)
    }

    private fun loadList(username: String?, shouldLoadFollowing: Boolean?) {
        viewModelScope.launch {
            usersRepository.fetchList(
                username ?: "",
                if (shouldLoadFollowing == true) LIST_TYPE_FOLLOWING else LIST_TYPE_FOLLOWERS
            ).let { list ->
                withContext(Dispatchers.Main) {
                    uiState = if (list.isEmpty()) {
                        uiState.copy(notFound = true)
                    } else {
                        uiState.copy(
                            list = list,
                            notFound = false
                        )
                    }
                }
            }
        }
    }


    private companion object {
        const val LIST_TYPE_FOLLOWING = "following"
        const val LIST_TYPE_FOLLOWERS = "followers"
    }
}