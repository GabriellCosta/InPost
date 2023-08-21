package pl.inpost.recruitmenttask.features.shipmentList.data.fetch

import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity
import javax.inject.Inject

internal class NonArchivedDecorator @Inject constructor() : FetchDecorators {
    override fun apply(item: List<ShipmentItemEntity>): List<ShipmentItemEntity> {
        return item.filter {
            !it.isArchived
        }
    }
}