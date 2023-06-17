package ext.aks4125.githubapicompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ext.aks4125.githubapicompose.R
import ext.aks4125.githubapicompose.util.Dimens.dimen_100
import ext.aks4125.githubapicompose.util.Dimens.dimen_16
import ext.aks4125.githubapicompose.util.Dimens.dimen_32

/**
 * An 8dp grid system. Smaller components can align to a 2dp 'sub' grid.
 */

@Composable
fun NoNetwork() {
    Box(
        modifier = Modifier
            .testTag("networkView")
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = dimen_32),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(dimen_100)
                    .padding(bottom = dimen_16),
                painter = painterResource(R.drawable.search_no_result),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.not_found),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
