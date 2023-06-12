package ext.aks4125.githubapicompose.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import ext.aks4125.githubapicompose.R
import ext.aks4125.githubapicompose.network.model.UserApiModel
import ext.aks4125.githubapicompose.ui.components.NoNetwork
import ext.aks4125.githubapicompose.ui.components.shimmerBrush

@Composable
internal fun UsersScreen(
    navController: NavHostController,
    viewModel: UserViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState
    val showShimmer = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ) {
        SearchBarSample(viewModel)

        Spacer(modifier = Modifier.height(4.dp))

//        viewModel.performSearch("JakeWharton")

        if (uiState.notFound) {
            NoNetwork()
        } else {
            Card(
                modifier = Modifier.padding(14.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.lightGrey))
            ) {
                uiState.user?.let {
                    UserItem(
                        item = it,
                        showShimmer,
                        viewModel,
                        navController
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchBarSample(viewModel: UserViewModel) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxWidth()) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .testTag("search")
                .semantics { this.contentDescription = "search view to search github user" },
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                active = false
                viewModel.performSearch(it)
            },

            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = { Text(text = "Search") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
            content = {})
    }

}

@Composable
fun UserItem(
    item: UserApiModel,
    showShimmer: MutableState<Boolean>,
    viewModel: UserViewModel,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(
                shimmerBrush(
                    targetValue = 1300f, showShimmer = showShimmer.value
                )
            )
            .width(150.dp)
            .height(150.dp),
            model = item.avatarUrl,
            contentDescription = null,
            onSuccess = { showShimmer.value = false })
        Column {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.username, item.userId),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                text = stringResource(id = R.string.profileName, item.name.toString()),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                text = item.bio.orEmpty(),
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 3
            )
        }
    }
    Row(modifier = Modifier.padding(start = 16.dp, top = 8.dp)) {
        ClickableText(
            text = AnnotatedString(
                stringResource(
                    id = R.string.followers, viewModel.formatNumber(item.followers ?: 0)
                )
            ),
            style = TextStyle(
                color = colorResource(R.color.blue),
                fontSize = 14.sp,
            ),
            onClick = {
                viewModel.navigateListScreen(item.userId, navController)
            }
        )

        ClickableText(
            modifier = Modifier.padding(start = 20.dp),
            text = AnnotatedString(
                stringResource(
                    id = R.string.following, viewModel.formatNumber(item.following ?: 0)
                )
            ),
            style = TextStyle(
                color = colorResource(R.color.blue),
                fontSize = 14.sp,
            ),
            onClick = {
                viewModel.navigateListScreen(item.userId, navController, following = true)
            }
        )
    }
}
