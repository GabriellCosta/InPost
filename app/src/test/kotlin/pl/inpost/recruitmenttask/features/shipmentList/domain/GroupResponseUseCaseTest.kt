package pl.inpost.recruitmenttask.features.shipmentList.domain

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemModel
import pl.inpost.recruitmenttask.features.shipmentList.domain.mapper.ShipmentItemModelToUiModelMapper
import pl.inpost.recruitmenttask.features.shipmentList.presenter.model.ShipmentItemType
import pl.inpost.recruitmenttask.features.shipmentList.presenter.model.ShipmentUiModel
import pl.inpost.recruitmenttask.features.shipmentList.ui.ShipmentItemUIModel

class GroupResponseUseCaseTest {

    private val mockMapper: ShipmentItemModelToUiModelMapper = mockk()
    private val mockSorter: SortShipmentItemsUseCase = mockk()
    private val subject: GroupResponseUseCase = GroupResponseUseCase(mockMapper, mockSorter)

    @Before
    fun setup() {
        every { mockSorter(any()) } answers { this.firstArg() }
    }

    private fun prepare(
        highlights: List<ShipmentItemModel>,
    ) {
        every { mockSorter(any()) } answers {
            this.firstArg()
        }

        val ui = highlights.map {
            mockk<ShipmentItemUIModel> {

            }
        }

        every { mockMapper.mapFrom(any()) } returnsMany ui
    }

    fun executeSubject(vararg parameters: Boolean): Flow<ShipmentUiModel> {
        val mappedParameters = parameters.map {
            mockk<ShipmentItemModel>(relaxed = true) {
                every { operationModel.highlight } returns it
            }
        }

        prepare(mappedParameters)

        return subject.invoke(flowOf(mappedParameters))
    }

    @Test
    fun `invoke with 2 highlight and one normal should return model with 5 items`() = runTest {
        val result = executeSubject(true, true, false)

        assertEquals(5, result.first().items.size)
    }

    @Test
    fun `invoke with 2 highlight and one normal should return model with first header correct`() = runTest {
        val expected = ShipmentItemType.HeaderItem(
            name = R.string.shipment_screen_item_separator_ready_to_pick_up,
        )

        val result = executeSubject(true, true, false)

        assertEquals(expected, result.first().items.first())
    }

    @Test
    fun `invoke with 2 highlight and one normal should return model with last header correct`() = runTest {
        val expected = ShipmentItemType.HeaderItem(
            name = R.string.shipment_screen_item_separator_not_ready_to_pick_up,
        )

        val result = executeSubject(true, true, false)

        assertEquals(expected, result.first().items[3])
    }

    @Test
    fun `invoke with 0 highlight and 3 normal should return model with last header only`() = runTest {
        val expected = ShipmentItemType.HeaderItem(
            name = R.string.shipment_screen_item_separator_not_ready_to_pick_up,
        )

        val result = executeSubject(false, false, false)

        assertEquals(expected, result.first().items[0])
    }

    @Test
    fun `invoke with 0 highlight and 3 normal should return model with 4 items`() = runTest {
        val expected = 4

        val result = executeSubject(false, false, false)

        assertEquals(expected, result.first().items.size)
    }

    @Test
    fun `invoke with 3 highlight and 0 normal should return model with first header only`() = runTest {
        val expected = ShipmentItemType.HeaderItem(
            name = R.string.shipment_screen_item_separator_not_ready_to_pick_up,
        )

        val result = executeSubject(true, true, true)

        assertFalse(result.first().items.contains(expected))
    }

    @Test
    fun `invoke with 3 highlight and 0 normal should return model with 4 items`() = runTest {
        val expected = 4

        val result = executeSubject(true, true, true)

        assertEquals(expected, result.first().items.size)
    }

    @Test
    fun `invoke with 0 highlight and 0 normal should return empty model`() = runTest {
        val expected = ShipmentUiModel(
            empty = true,
            items = emptyList(),
        )

        val result = executeSubject()

        assertEquals(expected, result.first())
    }
}
