package pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model

data class ShipmentUiModel(
    val items: List<ShipmentItemUiModel> = emptyList(),
)

data class ShipmentItemUiModel(
    val number: String,
    val status: String,
    val contact: String,
)