package pl.inpost.recruitmenttask.features.shipmentList.data.fetch

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity
import pl.inpost.recruitmenttask.network.model.ShipmentType

class ValidTypeDecoratorTest {

    private val subject = ValidTypeDecorator()

    @Test
    fun apply_with2validType_retrieveBoth() {
        val parameter = listOf(
            provideMock(type = ShipmentType.PARCEL_LOCKER.name),
            provideMock(type = ShipmentType.COURIER.name),
        )

        val result = subject.apply(parameter)

        assertEquals(parameter, result)
    }

    @Test
    fun apply_with2InvalidType_retrieveEmpty() {
        val parameter = listOf(
            provideMock(type = "invalid1"),
            provideMock(type = "invalid2"),
        )

        val result = subject.apply(parameter)

        assertTrue(result.isEmpty())
    }

    @Test
    fun apply_with1Archive_retrieveNonArchived() {
        val validType = provideMock(type = ShipmentType.COURIER.name)
        val parameter = listOf(
            provideMock(type = "invalid1"),
            validType,
        )
        val expected = listOf(validType)

        val result = subject.apply(parameter)

        assertEquals(expected, result)
    }

    private fun provideMock(type: String) = mockk<ShipmentItemEntity> {
        every { this@mockk.shipmentType } returns type
    }
}