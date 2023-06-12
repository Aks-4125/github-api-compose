package ext.aks4125.githubapicompose.ui.user

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import ext.aks4125.githubapicompose.InstantExecutorExtension
import ext.aks4125.githubapicompose.MainDispatcherRule
import ext.aks4125.githubapicompose.network.UserApi
import ext.aks4125.githubapicompose.network.model.UserApiModel
import ext.aks4125.githubapicompose.repository.UsersRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
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


    private lateinit var repository: UsersRepository

    @MockK
    private lateinit var api: UserApi

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var userViewModel: UserViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun performSearch() {
        runBlocking {
            val model = UserApiModel(
                userId = "akash",
                id = 213,
                avatarUrl = "",
                followers = 12,
                following = 11,
                name = "Akash",
                bio = "test bop",
                nodeId = "test",
                message = null
            )

            repository = UsersRepository(usersApi = api)
            every {
                savedStateHandle.get<String>(any())
            } returns ""

            coEvery {
                repository.fetchUser("akash")
            } coAnswers {
                model
            }

            userViewModel = UserViewModel(repository, savedStateHandle)

            Assert.assertNotNull(userViewModel.uiState)
            Assert.assertNotNull(userViewModel.uiState.notFound)
        }
    }
}
