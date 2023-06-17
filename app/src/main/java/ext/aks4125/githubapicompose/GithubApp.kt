package ext.aks4125.githubapicompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ext.aks4125.githubapicompose.ui.components.ScaffoldWithTopBar
import ext.aks4125.githubapicompose.ui.list.ListScreen
import ext.aks4125.githubapicompose.ui.user.UsersScreen


@Composable
fun GithubApp(
    navController: NavHostController = rememberNavController(),
    finish: () -> Unit
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            ScaffoldWithTopBar(navController) { finish() }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.ENTRY_USER,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Route.ENTRY_USER) {
                UsersScreen(navController)
            }
            composable(
                route = "${Route.LIST}/{${Argument.USERNAME}}/{${Argument.FOLLOWING}}",
                arguments = listOf(
                    navArgument(Argument.USERNAME) {
                        type = NavType.StringType
                    },
                    navArgument(Argument.FOLLOWING) {
                        type = NavType.BoolType
                    }
                )
            ) {
                ListScreen() { userId ->
                    navController.navigate(Route.ENTRY_USER + "/$userId")
                }
            }
            composable(
                route = "${Route.ENTRY_USER}/{${Argument.USERNAME}}",
                arguments = listOf(
                    navArgument(Argument.USERNAME) {
                        type = NavType.StringType
                    }
                )
            ) {
                UsersScreen(navController)
            }
        }
    }

}

object Route {
    const val ENTRY_USER = "user"
    const val LIST = "userList"
}

object Argument {
    const val USERNAME = "username"
    const val FOLLOWING = "following"
}
