package pl.inpost.recruitmenttask.features.shipmentList.data.fetch

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity

class NonArchivedDecoratorTest {

    private val subject = NonArchivedDecorator()

    @Test
    fun apply_with2NonArchive_retrieveBoth() {
        val parameter = listOf(provideMock(isArchived = false), provideMock(isArchived = false))

        val result = subject.apply(parameter)

        assertEquals(parameter, result)
    }

    @Test
    fun apply_with2Archive_retrieveEmpty() {
        val parameter = listOf(provideMock(isArchived = true), provideMock(isArchived = true))

        val result = subject.apply(parameter)

        assertTrue(result.isEmpty())
    }

    @Test
    fun apply_with1Archive_retrieveNonArchived() {
        val nonArchived = provideMock(isArchived = false)
        val parameter = listOf(provideMock(isArchived = true), nonArchived)
        val expected = listOf(nonArchived)

        val result = subject.apply(parameter)

        assertEquals(expected, result)
    }

    private fun provideMock(isArchived: Boolean) = mockk<ShipmentItemEntity> {
        every { this@mockk.isArchived } returns isArchived
    }
}