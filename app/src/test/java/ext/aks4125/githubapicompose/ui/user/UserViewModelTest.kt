package ext.aks4125.githubapicompose.ui.user

import androidx.lifecycle.SavedStateHandle
import ext.aks4125.githubapicompose.Argument
import ext.aks4125.githubapicompose.InstantExecutorExtension
import ext.aks4125.githubapicompose.MainDispatcherRule
import ext.aks4125.githubapicompose.network.model.UserApiModel
import ext.aks4125.githubapicompose.repository.UsersRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class UserViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = MainDispatcherRule()

    @MockK
    private lateinit var repository: UsersRepository

    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var userViewModel: UserViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        savedStateHandle = SavedStateHandle()
        savedStateHandle[Argument.USERNAME] = "test_user1"
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun performSearch() = runBlocking {
        val model = UserApiModel(
            userId = "test_user1",
            id = 213,
            avatarUrl = "",
            followers = 12,
            following = 11,
            name = "Test User",
            bio = "test bot",
            nodeId = "test",
            message = null
        )

        coEvery {
            repository.fetchUser("test_user1")
        } coAnswers { model }

        userViewModel = UserViewModel(repository, savedStateHandle)

        Assert.assertNotNull(userViewModel.uiState)

        Assert.assertFalse(userViewModel.uiState.notFound)
        Assert.assertEquals(userViewModel.uiState.user?.userId, "test_user1")

        coVerify(exactly = 1) {
            repository.fetchUser(any())
        }
    }

    @Test
    fun nullUserTest() = runBlocking {
        coEvery {
            repository.fetchUser("test_user1")
        } coAnswers {
            UserApiModel(
                userId = "undefined_user",
                id = 0,
                avatarUrl = "",
                followers = 0,
                following = 0,
                name = null,
                bio = null,
                nodeId = null,
                message = "user not found"
            )
        }
        userViewModel = UserViewModel(repository, savedStateHandle)

        Assert.assertTrue(userViewModel.uiState.notFound)

        coVerify(exactly = 1) {
            repository.fetchUser(any())
        }

    }

}
