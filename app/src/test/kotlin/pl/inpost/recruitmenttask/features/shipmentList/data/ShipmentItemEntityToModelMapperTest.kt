package pl.inpost.recruitmenttask.features.shipmentList.data

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemContactModel
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemModel
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemOperationModel
import pl.inpost.recruitmenttask.network.model.ShipmentStatus
import pl.inpost.recruitmenttask.network.model.ShipmentType

internal class ShipmentItemEntityToModelMapperTest {

    private lateinit var subject: ShipmentItemEntityToModelMapper

    @Before
    fun setUp() {
        subject = ShipmentItemEntityToModelMapper()
    }

    @Test
    fun mapFrom_validEntity_correctShipmentItemModel() {
        // Given
        val entity = ShipmentItemEntity(
            number = "123",
            status = "CREATED",
            shipmentType = "PARCEL_LOCKER",
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            email = "test@email.com",
            phoneNumber = "1234567890",
            name = "John Doe",
            manualArchive = true,
            delete = false,
            collect = true,
            highlight = false,
            expandAvizo = true,
            endOfWeekCollection = false
        )

        val expectedModel = ShipmentItemModel(
            number = "123",
            status = ShipmentStatus.CREATED,
            shipmentType = ShipmentType.PARCEL_LOCKER,
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            contact = ShipmentItemContactModel(
                email = "test@email.com",
                phoneNumber = "1234567890",
                name = "John Doe"
            ),
            operationModel = ShipmentItemOperationModel(
                manualArchive = true,
                delete = false,
                collect = true,
                highlight = false,
                expandAvizo = true,
                endOfWeekCollection = false
            )
        )

        // When
        val result = subject.mapFrom(entity)

        // Then
        assertEquals(expectedModel, result)
    }

    @Test
    fun mapFrom_entityWithNullFields_correctShipmentItemModel() {
        // Given
        val entity = ShipmentItemEntity(
            number = "123",
            status = "CREATED",
            shipmentType = "COURIER",
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            email = null,
            phoneNumber = null,
            name = null,
            manualArchive = true,
            delete = false,
            collect = true,
            highlight = false,
            expandAvizo = true,
            endOfWeekCollection = false
        )

        val expectedModel = ShipmentItemModel(
            number = "123",
            status = ShipmentStatus.CREATED,
            shipmentType = ShipmentType.COURIER,
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            contact = ShipmentItemContactModel(
                email = "",
                phoneNumber = "",
                name = ""
            ),
            operationModel = ShipmentItemOperationModel(
                manualArchive = true,
                delete = false,
                collect = true,
                highlight = false,
                expandAvizo = true,
                endOfWeekCollection = false
            )
        )

        // When
        val result = subject.mapFrom(entity)

        // Then
        assertEquals(expectedModel, result)
    }
}
