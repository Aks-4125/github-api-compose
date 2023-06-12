package ext.aks4125.githubapicompose.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import ext.aks4125.githubapicompose.R
import ext.aks4125.githubapicompose.network.model.UserApiModel
import ext.aks4125.githubapicompose.ui.components.NoNetwork
import ext.aks4125.githubapicompose.ui.components.shimmerBrush
import ext.aks4125.githubapicompose.util.Dimens.dimen_0
import ext.aks4125.githubapicompose.util.Dimens.dimen_10
import ext.aks4125.githubapicompose.util.Dimens.dimen_100
import ext.aks4125.githubapicompose.util.Dimens.dimen_16

@Composable
internal fun ListScreen(
    onUserClick: (String) -> Unit
) {
    val viewModel = hiltViewModel<ListViewModel>()
    val uiState = viewModel.uiState
    val showShimmer = remember { mutableStateOf(true) }


    Scaffold(
        containerColor = Color.White,
        modifier = Modifier.statusBarsPadding()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.background)
            ) {

                if (uiState.notFound) {
                    NoNetwork()
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        items(uiState.list) { item ->
                            UserItem(
                                item = item,
                                showShimmer,
                                onUserClick
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(dimen_10))
            }
        }
    }

}


@Composable
fun UserItem(
    item: UserApiModel,
    showShimmer: MutableState<Boolean>,
    onUserClick: (String) -> Unit
) {
    Card(
        modifier = Modifier.padding(dimen_10),
        shape = RoundedCornerShape(dimen_10),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.lightGrey))
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onUserClick(item.userId)
            }
            .padding(dimen_0),
            verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(modifier = Modifier
                .clip(RoundedCornerShape(20))
                .background(
                    shimmerBrush(
                        targetValue = 1300f, showShimmer = showShimmer.value
                    )
                )
                .width(dimen_100)
                .heightIn(min = dimen_100)
                .height(dimen_100),
                model = item.avatarUrl,
                contentDescription = null,
                onSuccess = { showShimmer.value = false })
            Column {
                Text(
                    modifier = Modifier.padding(start = dimen_16),
                    text = item.userId,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    modifier = Modifier.padding(start = dimen_16),
                    text = item.bio.orEmpty(),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

    }
}