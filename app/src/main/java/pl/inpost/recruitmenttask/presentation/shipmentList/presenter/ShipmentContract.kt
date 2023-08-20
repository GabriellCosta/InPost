package pl.inpost.recruitmenttask.presentation.shipmentList.presenter

interface ShipmentContract {

    fun invokeAction(action: ShipmentAction)
}

sealed interface ShipmentAction {

    data object Refresh: ShipmentAction

}