package pl.inpost.recruitmenttask.features.shipmentList.data

import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemContactModel
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemModel
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemOperationModel
import pl.inpost.recruitmenttask.network.model.ShipmentStatus
import pl.inpost.recruitmenttask.network.model.ShipmentType
import javax.inject.Inject

internal class ShipmentItemEntityToModelMapper @Inject constructor() {

    fun mapFrom(from: ShipmentItemEntity): ShipmentItemModel =
        ShipmentItemModel(
            number = from.number,
            status = ShipmentStatus.valueOf(from.status),
            shipmentType = ShipmentType.valueOf(from.shipmentType),
            expiryDate = from.expiryDate,
            storedDate = from.storedDate,
            pickUpDate = from.pickUpDate,
            contact = ShipmentItemContactModel(
                email = from.email.orEmpty(),
                phoneNumber = from.phoneNumber.orEmpty(),
                name = from.name.orEmpty()
            ),
            operationModel = ShipmentItemOperationModel(
                manualArchive = from.manualArchive,
                delete = from.delete,
                collect = from.collect,
                highlight = from.highlight,
                expandAvizo = from.expandAvizo,
                endOfWeekCollection = from.endOfWeekCollection,
            )
        )
}