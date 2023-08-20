package pl.inpost.recruitmenttask.presentation.shipmentList

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ItemSeparator

@Composable
fun ShipmentListScreen() {
    Surface {
        Column {
            ItemSeparator("Gotowe do odbioru")
        }
    }
}

