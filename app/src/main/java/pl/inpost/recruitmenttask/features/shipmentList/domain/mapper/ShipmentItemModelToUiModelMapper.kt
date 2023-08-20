package pl.inpost.recruitmenttask.features.shipmentList.domain.mapper

import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.network.model.ShipmentType
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemModel
import pl.inpost.recruitmenttask.features.shipmentList.presenter.ShipmentAction
import pl.inpost.recruitmenttask.features.shipmentList.ui.ShipmentItemDetailLabelUIModel
import pl.inpost.recruitmenttask.features.shipmentList.ui.ShipmentItemUIModel
import javax.inject.Inject

internal class ShipmentItemModelToUiModelMapper @Inject constructor(
    private val dateFormatter: ShipmentItemDateFormatter,
) {

    fun mapFrom(from: ShipmentItemModel): ShipmentItemUIModel {
        return ShipmentItemUIModel(
            number = from.number,
            status = from.status.nameRes,
            contact = from.contact.email,
            detail = getDetail(from),
            icon = mapIcon(from.shipmentType),
            archiveAction = ShipmentAction.Archive(from.number),
            isSwappable = from.operationModel.manualArchive,
        )
    }

    private fun getDetail(
        from: ShipmentItemModel
    ): ShipmentItemDetailLabelUIModel? {
        val date = when {
            from.pickUpDate != null -> {
                R.string.shipment_screen_item_date_waiting to from.pickUpDate
            }

            from.storedDate != null -> {
                R.string.shipment_screen_item_date_delivered to from.storedDate
            }

            else -> null
        }

        return date?.let {
            val formattedDate = dateFormatter.format(it.second)

            ShipmentItemDetailLabelUIModel(
                label = date.first,
                value = formattedDate,
            )
        }
    }

    private fun mapIcon(shipmentType: ShipmentType): Int =
        when (shipmentType) {
            ShipmentType.PARCEL_LOCKER -> R.drawable.ic_packing
            ShipmentType.COURIER -> R.drawable.ic_kurier
        }
}