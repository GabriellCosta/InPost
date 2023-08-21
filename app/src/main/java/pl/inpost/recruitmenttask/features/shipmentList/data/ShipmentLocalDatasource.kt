package pl.inpost.recruitmenttask.features.shipmentList.data

import pl.inpost.recruitmenttask.features.shipmentList.data.fetch.LocalFetchDecorator
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemModel
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemUpdateEntity
import pl.inpost.recruitmenttask.network.model.ShipmentNetwork
import javax.inject.Inject

internal class ShipmentLocalDatasource @Inject constructor(
    private val dao: ShipmentDao,
    private val fetchDecorators: LocalFetchDecorator,
    private val entityToModelMapper: ShipmentItemEntityToModelMapper,
    private val networkToEntityMapper: ShipmentNetworkToEntityMapper,
) {

    suspend fun saveAll(list: List<ShipmentNetwork>) {
        val mapped = list.map(networkToEntityMapper::mapFrom)

        dao.insertAll(mapped)
    }

    suspend fun fetch(): List<ShipmentItemModel> {
        return fetchDecorators
            .apply(dao.fetchShipment())
            .map(entityToModelMapper::mapFrom)
    }

    suspend fun archiveItem(number: String) {
        val toUpdate = ShipmentItemUpdateEntity(
            number = number,
            isArchived = true,
        )

        dao.updateShipment(toUpdate)
    }
}