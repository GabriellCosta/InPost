package pl.inpost.recruitmenttask.features.shipmentList.domain

import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemModel
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlin.math.absoluteValue

internal class SortShipmentItemsUseCase @Inject constructor() {

    operator fun invoke(shipmentItemModels: List<ShipmentItemModel>): List<ShipmentItemModel> {
        val now = ZonedDateTime.now()

        return shipmentItemModels.sortedWith(
            compareBy(
                { it.status.priority },  // Sort by status priority first
                {
                    it.pickUpDate?.until(
                        now,
                        ChronoUnit.SECONDS
                    )?.absoluteValue
                },  // Sort by proximity of pickupDate to now
                {
                    it.expiryDate?.until(
                        now,
                        ChronoUnit.SECONDS
                    )?.absoluteValue
                },  // Sort by proximity of expiryDate to now
                {
                    it.storedDate?.until(
                        now,
                        ChronoUnit.SECONDS
                    )?.absoluteValue
                },  // Sort by proximity of storedDate to now
                { it.number }  // Finally, sort by number
            )
        )
    }
}