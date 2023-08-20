package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.motion.utils.ViewState
import androidx.hilt.navigation.compose.hiltViewModel
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.ShipmentListViewModel
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentItemType
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentUiModel
import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ItemSeparator
import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ShipmentItemComposable
import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ShipmentItemUIModel
import pl.inpost.recruitmenttask.theme.InPostTheme

@Composable
internal fun ShipmentListScreen(
    viewModel: ShipmentListViewModel = hiltViewModel()
) {
    Scaffold(
        backgroundColor = Color(0xFFF2F2F2),
        topBar = {
            ShipmentTopBar()
        },
        content = { paddingValues ->
            ShipListScreenContent(viewModel.viewState.value)
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
private fun ShipListScreenContent(state: ShipmentUiModel) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(state.items) {
            when(it){
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

