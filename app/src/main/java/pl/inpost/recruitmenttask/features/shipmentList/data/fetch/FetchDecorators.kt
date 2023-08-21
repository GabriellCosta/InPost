package pl.inpost.recruitmenttask.features.shipmentList.data.fetch

import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity

internal interface FetchDecorators {

    fun apply(item: List<ShipmentItemEntity>): List<ShipmentItemEntity>
}