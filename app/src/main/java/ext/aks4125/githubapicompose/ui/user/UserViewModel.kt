package ext.aks4125.githubapicompose.ui.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import ext.aks4125.githubapicompose.Argument
import ext.aks4125.githubapicompose.Route
import ext.aks4125.githubapicompose.repository.UsersRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf(UsersUiState())
        private set

    private val username: String? = savedStateHandle[Argument.USERNAME]

    init {
        username?.let {
            performSearch(it)
        }
    }

    fun performSearch(it: String) {
        viewModelScope.launch {
            usersRepository.fetchUser(it).let { data ->
                    uiState = if (data.message != null) {
                        uiState.copy(notFound = true)
                    } else {
                        uiState.copy(
                            user = data,
                            notFound = false,
                        )
                    }
            }
        }
    }

    fun formatNumber(input: Int): String {
        return "%,d".format(input)
    }

    fun navigateListScreen(
        userId: String,
        navController: NavHostController,
        following: Boolean = false
    ) {
        navController.navigate("${Route.LIST}/$userId/$following")
    }
}
