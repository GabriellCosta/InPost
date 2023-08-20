package pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model

import androidx.annotation.StringRes
import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ShipmentItemUIModel

data class ShipmentUiModel(
    val loading: Boolean = true,
    val empty: Boolean = false,
    val items: List<ShipmentItemType> = emptyList(),
)

sealed interface ShipmentItemType {

    data class HeaderItem(@StringRes val name: Int) : ShipmentItemType

    data class ShipmentItem(val data: ShipmentItemUIModel) : ShipmentItemType
}