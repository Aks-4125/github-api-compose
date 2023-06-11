package ext.aks4125.githubapicompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ext.aks4125.githubapicompose.ui.ListScreen
import ext.aks4125.githubapicompose.ui.UsersScreen


@Composable
fun GithubApp(
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()


    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.ENTRY_USER,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Route.ENTRY_USER) {
                UsersScreen(navController)
            }
            composable(Route.LIST) {
                ListScreen(navController)
            }
        }
    }

}

object Route {
    const val ENTRY_USER = "user"
    const val LIST = "userList"
}