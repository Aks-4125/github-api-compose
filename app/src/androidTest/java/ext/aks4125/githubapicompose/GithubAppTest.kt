package ext.aks4125.githubapicompose

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ext.aks4125.githubapicompose.ui.theme.GithubApiComposeTheme
import ext.aks4125.githubapicompose.ui.user.UsersScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class GithubAppTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeRule.activity.setContent {
            GithubApiComposeTheme {
                val navController = rememberNavController()
                UsersScreen(navController = navController)
            }
        }
    }

    @Test
    fun testComponent() {
        composeRule
            .onNode(hasTestTag("search"))
            .assertExists()
        composeRule
            .onNode(hasTestTag("search"))
            .performClick()
            .assertContentDescriptionEquals("search view to search github user")
    }

}