package pl.inpost.recruitmenttask.features.shipmentList.data.fetch

import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity
import pl.inpost.recruitmenttask.network.model.ShipmentStatus
import javax.inject.Inject

internal class ValidStatusDecorator @Inject constructor() : FetchDecorators {
    override fun apply(item: List<ShipmentItemEntity>): List<ShipmentItemEntity> {
        return item.filter {
            try {
                ShipmentStatus.valueOf(it.status)
                true
            } catch (ex: Exception) {
                // TODO: Add remote logger here to alert for new types being send from BE
                false
            }
        }
    }
}