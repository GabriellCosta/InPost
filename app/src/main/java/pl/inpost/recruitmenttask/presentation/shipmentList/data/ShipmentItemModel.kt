package pl.inpost.recruitmenttask.presentation.shipmentList.data

import pl.inpost.recruitmenttask.network.model.ShipmentStatus
import pl.inpost.recruitmenttask.network.model.ShipmentType
import java.time.ZonedDateTime

internal data class ShipmentItemModel(
    val number: String,
    val status: ShipmentStatus,
    val shipmentType: ShipmentType,
    val expiryDate: ZonedDateTime?,
    val storedDate: ZonedDateTime?,
    val pickUpDate: ZonedDateTime?,
    val contact: ShipmentItemContactModel,
    val operationModel: ShipmentItemOperationModel,
)

internal data class ShipmentItemContactModel(
    val email: String,
    val phoneNumber: String,
    val name: String,
)

internal data class ShipmentItemOperationModel(
    val manualArchive: Boolean,
    val delete: Boolean,
    val collect: Boolean,
    val highlight: Boolean,
    val expandAvizo: Boolean,
    val endOfWeekCollection: Boolean,
)