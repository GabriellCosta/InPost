package pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model

import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ShipmentItemUIModel

data class ShipmentUiModel(
    val items: List<ShipmentItemType> = emptyList(),
)

sealed interface ShipmentItemType {

    data class HeaderItem(val name: String) : ShipmentItemType

    data class ShipmentItem(val data: ShipmentItemUIModel) : ShipmentItemType
}