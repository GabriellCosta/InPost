package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.ShipmentListViewModel
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentItemType
import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ItemSeparator
import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ShipmentItemComposable

@Composable
fun ShipmentListScreen(
    viewModel: ShipmentListViewModel = hiltViewModel()
) {
    val state by remember { viewModel.viewState }

    Surface(
        color = Color(0xFFF2F2F2)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(state.items) {
                when(it){
                    is ShipmentItemType.HeaderItem -> {
                        ItemSeparator(text = it.name)
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
}

