package pl.inpost.recruitmenttask.features.shipmentList.data.fetch

import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity
import javax.inject.Inject

internal class LocalFetchDecorator @Inject constructor(
    private val set: Set<@JvmSuppressWildcards FetchDecorators>,
) {

    fun apply(item: List<ShipmentItemEntity>): List<ShipmentItemEntity> {
        return set.fold(item) { acc, decorator ->
            decorator.apply(acc)
        }
    }
}