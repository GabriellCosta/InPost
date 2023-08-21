package pl.inpost.recruitmenttask.features.shipmentList.data.fetch

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity
import pl.inpost.recruitmenttask.network.model.ShipmentStatus

class ValidStatusDecoratorTest {

    private val subject = ValidStatusDecorator()

    @Test
    fun apply_with2validStatus_retrieveBoth() {
        val parameter = listOf(
            provideMock(status = ShipmentStatus.CREATED.name),
            provideMock(status = ShipmentStatus.CREATED.name),
        )

        val result = subject.apply(parameter)

        assertEquals(parameter, result)
    }

    @Test
    fun apply_with2InvalidStatus_retrieveEmpty() {
        val parameter = listOf(
            provideMock(status = "invalid1"),
            provideMock(status = "invalid2"),
        )

        val result = subject.apply(parameter)

        assertTrue(result.isEmpty())
    }

    @Test
    fun apply_with1Archive_retrieveNonArchived() {
        val validStatus = provideMock(status = ShipmentStatus.CREATED.name)
        val parameter = listOf(
            provideMock(status = "invalid1"),
            validStatus,
        )
        val expected = listOf(validStatus)

        val result = subject.apply(parameter)

        assertEquals(expected, result)
    }

    private fun provideMock(status: String) = mockk<ShipmentItemEntity> {
        every { this@mockk.status } returns status
    }
}