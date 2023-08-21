package pl.inpost.recruitmenttask.features.shipmentList.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemContactModel
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemModel
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemOperationModel
import pl.inpost.recruitmenttask.network.model.ShipmentStatus
import pl.inpost.recruitmenttask.network.model.ShipmentType
import java.time.ZonedDateTime

internal class SortShipmentItemsUseCaseTest {

    private val subject = SortShipmentItemsUseCase()

    @Test
    fun invoke_sort_statusPriority() {
        val shipmentA = shipmentItemModel(status = ShipmentStatus.PICKUP_TIME_EXPIRED, number = "1")
        val shipmentB = shipmentItemModel(status = ShipmentStatus.CREATED, number = "2")
        val expected = listOf(shipmentB, shipmentA)

        val result = subject.invoke(listOf(shipmentA, shipmentB))

        assertEquals(expected, result)
    }

    @Test
    fun invoke_sort_proximityOfPickUpDate() {
        val now = ZonedDateTime.now()
        val shipmentA = shipmentItemModel(pickUpDate = now.plusDays(1), number = "1")
        val shipmentB = shipmentItemModel(pickUpDate = now.plusDays(2), number = "2")
        val expected = listOf(shipmentA, shipmentB)

        val result = subject.invoke(listOf(shipmentB, shipmentA))

        assertEquals(expected, result)
    }

    @Test
    fun invoke_sort_proximityOfExpiryDate() {
        val now = ZonedDateTime.now()
        val shipmentA = shipmentItemModel(expiryDate = now.plusDays(1), number = "1")
        val shipmentB = shipmentItemModel(expiryDate = now.plusDays(2), number = "2")
        val expected = listOf(shipmentA, shipmentB)

        val result = subject.invoke(listOf(shipmentB, shipmentA))

        assertEquals(expected, result)
    }

    @Test
    fun invoke_sort_proximityOfStoredDate() {
        val now = ZonedDateTime.now()
        val shipmentA = shipmentItemModel(storedDate = now.minusDays(1), number = "1")
        val shipmentB = shipmentItemModel(storedDate = now.minusDays(2), number = "2")
        val expected = listOf(shipmentA, shipmentB)

        val result = subject.invoke(listOf(shipmentB, shipmentA))

        assertEquals(expected, result)
    }

    @Test
    fun invoke_sort_shipmentNumber() {
        val shipmentA = shipmentItemModel(number = "1")
        val shipmentB = shipmentItemModel(number = "2")
        val expected = listOf(shipmentA, shipmentB)

        val result = subject.invoke(listOf(shipmentB, shipmentA))

        assertEquals(expected, result)
    }

    @Test
    fun invoke_sort_fullList() {
        val shipment1 = shipmentItemModel(
            number = "26730345345597442248333",
            storedDate = ZonedDateTime.parse("2022-11-29T04:56:07Z"),
            expiryDate = ZonedDateTime.parse("2022-11-29T04:56:07Z"),
            status = ShipmentStatus.CONFIRMED
        )

        val shipment2 = shipmentItemModel(
            number = "46730345345597442248333",
            storedDate = ZonedDateTime.parse("2022-11-25T04:56:07Z"),
            expiryDate = ZonedDateTime.parse("2022-12-05T04:56:07Z"),
            status = ShipmentStatus.OUT_FOR_DELIVERY
        )

        val shipment3 = shipmentItemModel(
            number = "56730345345597442248333",
            storedDate = ZonedDateTime.parse("2022-11-25T04:56:07Z"),
            expiryDate = ZonedDateTime.parse("2022-12-05T04:56:07Z"),
            status = ShipmentStatus.OUT_FOR_DELIVERY
        )

        val shipment5 = shipmentItemModel(
            number = "76730345345597442248333",
            storedDate = ZonedDateTime.parse("2021-07-29T04:56:07Z"),
            expiryDate = ZonedDateTime.parse("2021-12-29T04:56:07Z"),
            status = ShipmentStatus.SENT_FROM_SOURCE_BRANCH
        )

        val shipment6 = shipmentItemModel(
            number = "86730345345597442248333",
            storedDate = ZonedDateTime.parse("2022-11-04T04:56:07Z"),
            expiryDate = ZonedDateTime.parse("2022-11-05T04:56:07Z"),
            status = ShipmentStatus.AVIZO
        )

        val shipment7 = shipmentItemModel(
            number = "96730345345597442248333",
            pickUpDate = ZonedDateTime.parse("2022-11-05T04:56:07Z"),
            status = ShipmentStatus.CONFIRMED
        )

        val shipments =
            listOf(shipment1, shipment2, shipment3, shipment5, shipment6, shipment7)
        val expected =
            listOf(shipment1, shipment7, shipment5, shipment6, shipment2, shipment3)


        val result = subject.invoke(shipments)

        assertEquals(expected, result)
    }

    private fun shipmentItemModel(
        number: String,
        storedDate: ZonedDateTime? = null,
        expiryDate: ZonedDateTime? = null,
        pickUpDate: ZonedDateTime? = null,
        status: ShipmentStatus = ShipmentStatus.CREATED,
    ) = ShipmentItemModel(
        number = number,
        status = status,
        shipmentType = ShipmentType.COURIER,
        expiryDate = expiryDate,
        storedDate = storedDate,
        pickUpDate = pickUpDate,
        contact = ShipmentItemContactModel(
            email = "leonor.mayer@example.com",
            phoneNumber = "(510) 290-1347",
            name = "Kristin Guerrero"
        ),
        operationModel = ShipmentItemOperationModel(
            manualArchive = false,
            delete = false,
            collect = false,
            highlight = false,
            expandAvizo = false,
            endOfWeekCollection = false
        ),
    )
}
