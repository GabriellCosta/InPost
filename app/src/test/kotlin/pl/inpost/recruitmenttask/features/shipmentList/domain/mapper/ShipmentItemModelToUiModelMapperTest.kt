package pl.inpost.recruitmenttask.features.shipmentList.domain.mapper

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.features.shipmentList.data.ShipmentItemContactModel
import pl.inpost.recruitmenttask.features.shipmentList.data.ShipmentItemModel
import pl.inpost.recruitmenttask.features.shipmentList.data.ShipmentItemOperationModel
import pl.inpost.recruitmenttask.features.shipmentList.presenter.ShipmentAction
import pl.inpost.recruitmenttask.features.shipmentList.ui.ShipmentItemDetailLabelUIModel
import pl.inpost.recruitmenttask.features.shipmentList.ui.ShipmentItemUIModel
import pl.inpost.recruitmenttask.network.model.ShipmentStatus
import pl.inpost.recruitmenttask.network.model.ShipmentType
import java.time.ZonedDateTime

internal class ShipmentItemModelToUiModelMapperTest {

    private lateinit var dateFormatter: ShipmentItemDateFormatter
    private lateinit var subject: ShipmentItemModelToUiModelMapper

    val mockDateFormatted = "20.08.23 | 12:34"

    @Before
    fun setUp() {
        dateFormatter = mockk()
        subject = ShipmentItemModelToUiModelMapper(dateFormatter)

        every { dateFormatter.format(any()) } returns mockDateFormatted
    }

    @Test
    fun mapFrom_pickUpDateNonNull_correctShipmentItemUIModel() {
        val mockModel = ShipmentItemModel(
            number = "123",
            status = ShipmentStatus.AVIZO,
            contact = ShipmentItemContactModel(
                email = "test@email.com",
                phoneNumber = "(539) 497-3849",
                name = "Derick Pearson",
            ),
            operationModel = ShipmentItemOperationModel(
                manualArchive = true,
                delete = true,
                collect = true,
                highlight = true,
                expandAvizo = true,
                endOfWeekCollection = true
            ),
            shipmentType = ShipmentType.PARCEL_LOCKER,
            pickUpDate = ZonedDateTime.now(),
            storedDate = null,
            expiryDate = null,
        )

        val expectedUIModel = ShipmentItemUIModel(
            number = "123",
            status = R.string.status_avizo,
            contact = "test@email.com",
            detail = ShipmentItemDetailLabelUIModel(
                label = R.string.shipment_screen_item_date_waiting,
                value = mockDateFormatted
            ),
            icon = R.drawable.ic_packing,
            archiveAction = ShipmentAction.Archive("123"),
            isSwappable = true
        )

        val result = subject.mapFrom(mockModel)

        assertEquals(expectedUIModel, result)
    }

    @Test
    fun mapFrom_storedDateNonNull_correctShipmentItemUIModel() {
        val mockModel = ShipmentItemModel(
            number = "123",
            status = ShipmentStatus.DELIVERED,
            contact = ShipmentItemContactModel(
                email = "test@email.com",
                phoneNumber = "(539) 497-3849",
                name = "Derick Pearson",
            ),
            operationModel = ShipmentItemOperationModel(
                manualArchive = true,
                delete = false,
                collect = false,
                highlight = false,
                expandAvizo = false,
                endOfWeekCollection = false
            ),
            shipmentType = ShipmentType.COURIER,
            pickUpDate = null,
            storedDate = ZonedDateTime.now(),
            expiryDate = null,
        )

        val expectedUIModel = ShipmentItemUIModel(
            number = "123",
            status = R.string.status_delivered,
            contact = "test@email.com",
            detail = ShipmentItemDetailLabelUIModel(
                label = R.string.shipment_screen_item_date_delivered,
                value = mockDateFormatted
            ),
            icon = R.drawable.ic_kurier,
            archiveAction = ShipmentAction.Archive("123"),
            isSwappable = true
        )

        val result = subject.mapFrom(mockModel)

        assertEquals(expectedUIModel, result)
    }

    @Test
    fun mapFrom_noDates_correctShipmentItemUIModelWithNoDetail() {
        val mockModel = ShipmentItemModel(
            number = "123",
            status = ShipmentStatus.CONFIRMED,
            contact = ShipmentItemContactModel(
                email = "test@email.com",
                phoneNumber = "(539) 497-3849",
                name = "Derick Pearson",
            ),
            operationModel = ShipmentItemOperationModel(
                manualArchive = true,
                delete = false,
                collect = false,
                highlight = false,
                expandAvizo = false,
                endOfWeekCollection = false
            ),
            shipmentType = ShipmentType.PARCEL_LOCKER,
            pickUpDate = null,
            storedDate = null,
            expiryDate = null,
        )

        val expectedUIModel = ShipmentItemUIModel(
            number = "123",
            status = R.string.status_confirmed,
            contact = "test@email.com",
            detail = null,
            icon = R.drawable.ic_packing,
            archiveAction = ShipmentAction.Archive("123"),
            isSwappable = true
        )

        val result = subject.mapFrom(mockModel)

        assertEquals(expectedUIModel, result)
    }

    @Test
    fun mapFrom_Notachievable_correctShipmentItemUIModelWithSwappable() {
        val mockModel = ShipmentItemModel(
            number = "123",
            status = ShipmentStatus.CONFIRMED,
            contact = ShipmentItemContactModel(
                email = "test@email.com",
                phoneNumber = "(539) 497-3849",
                name = "Derick Pearson",
            ),
            operationModel = ShipmentItemOperationModel(
                manualArchive = false,
                delete = false,
                collect = false,
                highlight = false,
                expandAvizo = false,
                endOfWeekCollection = false
            ),
            shipmentType = ShipmentType.PARCEL_LOCKER,
            pickUpDate = null,
            storedDate = null,
            expiryDate = null,
        )

        val expectedUIModel = ShipmentItemUIModel(
            number = "123",
            status = R.string.status_confirmed,
            contact = "test@email.com",
            detail = null,
            icon = R.drawable.ic_packing,
            archiveAction = ShipmentAction.Archive("123"),
            isSwappable = false
        )

        val result = subject.mapFrom(mockModel)

        assertEquals(expectedUIModel, result)
    }

    // ... You can also add more test cases if there are other scenarios or edge cases you want to cover.
}
