package pl.inpost.recruitmenttask.features.shipmentList.data.fetch

import io.mockk.MockKAnswerScope
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity

class LocalFetchDecoratorTest {

    private val set = mutableSetOf<FetchDecorators>()

    private val subject = LocalFetchDecorator(set)

    @After
    fun tearDown() {
        set.clear()
    }

    @Test
    fun apply_executeAllDecorators() {
        val element = prepareMock()
        val element1 = prepareMock()
        set.add(element)
        set.add(element1)
        val item = listOf<ShipmentItemEntity>()

        subject.apply(item)

        verify(exactly = 1) {
            element.apply(item)
            element1.apply(item)
        }
    }

    @Test
    fun apply_executeLogic() {
        val removeFirst = prepareMock {
            firstArg<List<ShipmentItemEntity>>()
                .toMutableList()
                .also {
                    it.removeFirst()
                }
        }
        val removeLast = prepareMock {
            firstArg<List<ShipmentItemEntity>>()
                .toMutableList()
                .also {
                    it.removeLast()
                }
        }
        set.add(removeFirst)
        set.add(removeLast)
        val first = mockk<ShipmentItemEntity>()
        val second = mockk<ShipmentItemEntity>()
        val third = mockk<ShipmentItemEntity>()
        val item = listOf(first, second, third)
        val expected = listOf(second)

        val result = subject.apply(item)

        assertEquals(expected, result)
    }

    private fun prepareMock(response: List<ShipmentItemEntity> = emptyList()) =
        mockk<FetchDecorators> {
            every { this@mockk.apply(any()) } returns response
        }

    private fun prepareMock(
        function: MockKAnswerScope<List<ShipmentItemEntity>, List<ShipmentItemEntity>>.(Any) -> List<ShipmentItemEntity>
    ) =
        mockk<FetchDecorators> {
            every { this@mockk.apply(any()) } answers function
        }
}