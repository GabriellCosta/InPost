package pl.inpost.recruitmenttask.features.shipmentList.data

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity
import pl.inpost.recruitmenttask.network.model.CustomerNetwork
import pl.inpost.recruitmenttask.network.model.OperationsNetwork
import pl.inpost.recruitmenttask.network.model.ShipmentNetwork

internal class ShipmentNetworkToEntityMapperTest {

    private lateinit var subject: ShipmentNetworkToEntityMapper

    @Before
    fun setUp() {
        subject = ShipmentNetworkToEntityMapper()
    }

    @Test
    fun mapFrom_validNetwork_correctShipmentItemEntity() {
        // Given
        val network = ShipmentNetwork(
            number = "123",
            status = "ACTIVE",
            shipmentType = "PARCEL_LOCKER",
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            sender = CustomerNetwork(
                email = "test@email.com",
                phoneNumber = "1234567890",
                name = "John Doe"
            ),
            operations = OperationsNetwork(
                manualArchive = true,
                delete = false,
                collect = true,
                highlight = false,
                expandAvizo = true,
                endOfWeekCollection = false
            ),
            openCode = null,
            eventLog = emptyList(),
            receiver = null,
        )

        val expectedEntity = ShipmentItemEntity(
            number = "123",
            status = "ACTIVE",
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

        // When
        val result = subject.mapFrom(network)

        // Then
        assertEquals(expectedEntity, result)
    }

    @Test
    fun mapFrom_networkWithNullSender_correctShipmentItemEntity() {
        // Given
        val network = ShipmentNetwork(
            number = "123",
            status = "ACTIVE",
            shipmentType = "PARCEL_LOCKER",
            expiryDate = null,
            storedDate = null,
            pickUpDate = null,
            sender = null,
            operations = OperationsNetwork(
                manualArchive = true,
                delete = false,
                collect = true,
                highlight = false,
                expandAvizo = true,
                endOfWeekCollection = false
            ),
            openCode = null,
            eventLog = emptyList(),
            receiver = null,
        )

        val expectedEntity = ShipmentItemEntity(
            number = "123",
            status = "ACTIVE",
            shipmentType = "PARCEL_LOCKER",
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

        // When
        val result = subject.mapFrom(network)

        // Then
        assertEquals(expectedEntity, result)
    }
}
