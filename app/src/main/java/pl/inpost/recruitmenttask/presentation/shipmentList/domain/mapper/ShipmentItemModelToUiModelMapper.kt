package pl.inpost.recruitmenttask.presentation.shipmentList.domain.mapper

import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.network.model.ShipmentType
import pl.inpost.recruitmenttask.presentation.shipmentList.data.ShipmentItemModel
import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ShipmentItemUIModel
import javax.inject.Inject

internal class ShipmentItemModelToUiModelMapper @Inject constructor() {

    fun mapFrom(from: ShipmentItemModel): ShipmentItemUIModel {
        return ShipmentItemUIModel(
            number = from.number,
            status = from.status.nameRes,
            contact = from.contact.email,
            detail = null,
            icon = mapIcon(from.shipmentType),
        )
    }

    private fun mapIcon(shipmentType: ShipmentType): Int =
        when (shipmentType) {
            ShipmentType.PARCEL_LOCKER -> R.drawable.ic_packing
            ShipmentType.COURIER -> R.drawable.ic_kurier
        }
}