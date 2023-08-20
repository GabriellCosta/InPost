package pl.inpost.recruitmenttask.features.shipmentList.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import pl.inpost.recruitmenttask.network.api.ShipmentApi
import javax.inject.Inject

internal class ShipmentRepository @Inject constructor(
    private val api: ShipmentApi,
    private val localData: ShipmentLocalDatasource,
) {

    suspend fun fetchShipments(
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

    suspend fun archiveShipment(number: String): Flow<List<ShipmentItemModel>> {
        localData.archiveItem(number)

        return flowOf(localData.fetch())
    }
}