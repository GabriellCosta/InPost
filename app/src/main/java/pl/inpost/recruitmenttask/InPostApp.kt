package pl.inpost.recruitmenttask

import androidx.compose.runtime.Composable
import pl.inpost.recruitmenttask.features.shipmentList.ShipmentListScreen
import pl.inpost.recruitmenttask.theme.InPostTheme

@Composable
fun InPostApp() {
    InPostTheme {
        ShipmentListScreen()
    }
}