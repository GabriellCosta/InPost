package pl.inpost.recruitmenttask.features.shipmentList.data

import pl.inpost.recruitmenttask.features.shipmentList.data.fetch.FetchDecorators
import pl.inpost.recruitmenttask.features.shipmentList.data.fetch.LocalFetchDecorator
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemContactModel
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemModel
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemOperationModel
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemUpdateEntity
import pl.inpost.recruitmenttask.network.model.ShipmentNetwork
import pl.inpost.recruitmenttask.network.model.ShipmentStatus
import pl.inpost.recruitmenttask.network.model.ShipmentType
import javax.inject.Inject

internal class ShipmentLocalDatasource @Inject constructor(
    private val dao: ShipmentDao,
    private val fetchDecorators: LocalFetchDecorator,
) {

    suspend fun saveAll(list: List<ShipmentNetwork>) {
        val mapped = list.map {
            ShipmentItemEntity(
                number = it.number,
                status = it.status,
                shipmentType = it.shipmentType,
                expiryDate = it.expiryDate,
                storedDate = it.storedDate,
                pickUpDate = it.pickUpDate,
                email = it.sender?.email,
                phoneNumber = it.sender?.phoneNumber,
                name = it.sender?.name,
                manualArchive = it.operations.manualArchive,
                delete = it.operations.delete,
                collect = it.operations.collect,
                highlight = it.operations.highlight,
                expandAvizo = it.operations.expandAvizo,
                endOfWeekCollection = it.operations.endOfWeekCollection,
            )
        }

        dao.insertAll(mapped)
    }

    suspend fun fetch(): List<ShipmentItemModel> {
        return fetchDecorators.apply(dao.fetchShipment())
            .map {
                ShipmentItemModel(
                    number = it.number,
                    status = ShipmentStatus.valueOf(it.status),
                    shipmentType = ShipmentType.valueOf(it.shipmentType),
                    expiryDate = it.expiryDate,
                    storedDate = it.storedDate,
                    pickUpDate = it.pickUpDate,
                    contact = ShipmentItemContactModel(
                        email = it.email.orEmpty(),
                        phoneNumber = it.phoneNumber.orEmpty(),
                        name = it.name.orEmpty()
                    ),
                    operationModel = ShipmentItemOperationModel(
                        manualArchive = it.manualArchive,
                        delete = it.delete,
                        collect = it.collect,
                        highlight = it.highlight,
                        expandAvizo = it.expandAvizo,
                        endOfWeekCollection = it.endOfWeekCollection,
                    )

                )
            }
    }

    suspend fun archiveItem(number: String) {
        val toUpdate = ShipmentItemUpdateEntity(
            number = number,
            isArchived = true,
        )

        dao.updateShipment(toUpdate)
    }
}