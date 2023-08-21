package pl.inpost.recruitmenttask.features.shipmentList.data

import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity
import pl.inpost.recruitmenttask.network.model.ShipmentNetwork
import javax.inject.Inject

internal class ShipmentNetworkToEntityMapper @Inject constructor() {

    fun mapFrom(from: ShipmentNetwork): ShipmentItemEntity =
        ShipmentItemEntity(
            number = from.number,
            status = from.status,
            shipmentType = from.shipmentType,
            expiryDate = from.expiryDate,
            storedDate = from.storedDate,
            pickUpDate = from.pickUpDate,
            email = from.sender?.email,
            phoneNumber = from.sender?.phoneNumber,
            name = from.sender?.name,
            manualArchive = from.operations.manualArchive,
            delete = from.operations.delete,
            collect = from.operations.collect,
            highlight = from.operations.highlight,
            expandAvizo = from.operations.expandAvizo,
            endOfWeekCollection = from.operations.endOfWeekCollection,
        )
}