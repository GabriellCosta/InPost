package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.ShipmentListViewModel
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentItemType
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentUiModel
import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ItemSeparator
import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ShipmentItemComposable
import pl.inpost.recruitmenttask.theme.InPostTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ShipmentListScreen(
    viewModel: ShipmentListViewModel = hiltViewModel()
) {
    val viewState by remember { viewModel.viewState }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = viewState.loading,
        onRefresh = {
            viewModel.invokeActions()
        },
    )

    Scaffold(
        backgroundColor = Color(0xFFF2F2F2),
        topBar = {
            ShipmentTopBar()
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .pullRefresh(pullRefreshState)
            ) {
                ShipListScreenContent(
                    state = viewState
                )

                PullRefreshIndicator(
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    refreshing = viewState.loading,
                    state = pullRefreshState,
                )
            }
        }
    )
}

@Composable
private fun ShipmentTopBar() {
    TopAppBar(
        contentPadding = PaddingValues(start = 20.dp),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            color = Color(0xFF404041),
            style = InPostTheme.typography.bodyLarge,
        )
    }
}

@Composable
private fun ShipListScreenContent(
    modifier: Modifier = Modifier,
    state: ShipmentUiModel
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(state.items) {
            when (it) {
                is ShipmentItemType.HeaderItem -> {
                    ItemSeparator(text = stringResource(id = it.name))
                }

                is ShipmentItemType.ShipmentItem -> {
                    ShipmentItemComposable(
                        modifier = Modifier.fillMaxWidth(),
                        model = it.data,
                    )
                }
            }
        }
    }
}

