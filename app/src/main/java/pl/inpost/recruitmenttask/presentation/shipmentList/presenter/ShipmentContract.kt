package pl.inpost.recruitmenttask.presentation.shipmentList.presenter

interface ShipmentContract {

    fun invokeAction(action: ShipmentAction)

}

sealed interface ShipmentAction {

    data object InitialAction: ShipmentAction

    data object Refresh: ShipmentAction

    data class Archive(val id: String): ShipmentAction
}