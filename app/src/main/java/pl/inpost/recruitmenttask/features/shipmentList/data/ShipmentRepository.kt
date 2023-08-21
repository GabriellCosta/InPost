package pl.inpost.recruitmenttask.features.shipmentList.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemModel
import pl.inpost.recruitmenttask.network.api.ShipmentApi
import javax.inject.Inject

internal class ShipmentRepository @Inject constructor(
    private val api: ShipmentApi,
    private val localData: ShipmentLocalDatasource,
) {

    fun fetchShipments(
        refresh: Boolean = false,
    ): Flow<List<ShipmentItemModel>> {
        return flow {
            if (!refresh) {
                emit(localData.fetch())
            }
            val apiResult = api.getShipments()
            localData.saveAll(apiResult)

            emit(localData.fetch())
        }
    }

    fun archiveShipment(number: String): Flow<List<ShipmentItemModel>> {
        return flow {
            localData.archiveItem(number)

            emit(localData.fetch())
        }
    }
}