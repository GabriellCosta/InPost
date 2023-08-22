package pl.inpost.recruitmenttask.features.shipmentList.data

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemModel
import pl.inpost.recruitmenttask.network.api.ShipmentApi
import pl.inpost.recruitmenttask.network.model.ShipmentNetwork

class ShipmentRepositoryTest {

    private val api = mockk<ShipmentApi>()
    private val localData = mockk<ShipmentLocalDatasource>()

    private val subject = ShipmentRepository(
        api,
        localData,
    )

    @Before
    fun setup() {
        coEvery { localData.saveAll(any()) } just runs
        coEvery { localData.archiveItem(any()) } just runs
    }

    @Test
    fun fetchShipments_refreshAsFalse_returnLocalDataThenApiData() = runTest {
        val apiResponse = listOf<ShipmentNetwork>(mockk(), mockk())
        val localResponseFromApi = apiResponse.toLocalResponse()
        val previousLocal = listOf<ShipmentItemModel>(mockk())
        prepare(
            apiResponse = apiResponse,
            localResponse = listOf(
                previousLocal,
                localResponseFromApi
            )
        )
        val expected = listOf(previousLocal, localResponseFromApi)

        val result = subject.fetchShipments(false).toList()

        assertEquals(expected, result)
    }

    @Test
    fun fetchShipments_refreshAsTrue_returnApiDataOnly() = runTest {
        val apiResponse = listOf<ShipmentNetwork>(mockk(), mockk())
        val localResponseFromApi = apiResponse.toLocalResponse()
        prepare(
            apiResponse = apiResponse,
            localResponse = listOf(
                localResponseFromApi
            )
        )
        val expected = listOf(localResponseFromApi)

        val result = subject.fetchShipments(true).toList()

        assertEquals(expected, result)
    }

    //TODO: To better test this we would need something near a faker for or DAO
    @Test
    fun fetchShipments_refreshAsFalseAndApiError_returnLocalDataOnly() = runTest {
        val apiResponse = listOf<ShipmentNetwork>(mockk(), mockk())
        val localResponseFromApi = apiResponse.toLocalResponse()
        val previousLocal = listOf<ShipmentItemModel>(mockk())
        prepare(
            apiResponse = Exception(),
            localResponse = listOf(
                previousLocal,
                previousLocal
            )
        )
        listOf(previousLocal, localResponseFromApi)


        subject.fetchShipments(false).collect()

        coVerify(exactly = 1) {
            localData.saveAll(any())
        }
    }

    @Test
    fun archiveShipment_shouldEmitLocalResponse() = runTest {
        val localResponse = listOf<ShipmentItemModel>(mockk())
        prepare(
            localResponse = listOf(localResponse),

        )
        val expected = listOf(localResponse)

        val result = subject.archiveShipment("123").toList()

        assertEquals(expected, result)
    }

    @Test
    fun archiveShipment_shouldArchiveItem() = runTest {
        val localResponse = listOf<ShipmentItemModel>(mockk())
        prepare(
            localResponse = listOf(localResponse),

            )
        val expected = listOf(localResponse)

        subject.archiveShipment("123").collect()

        coVerify(exactly = 1) {
            localData.archiveItem("123")
        }
    }

    private fun List<ShipmentNetwork>.toLocalResponse(): List<ShipmentItemModel> {
        return map {
            mockk()
        }
    }

    private fun prepare(
        apiResponse: Throwable,
        localResponse: List<List<ShipmentItemModel>>,
    ) {
        prepare(
            emptyList(),
            localResponse
        )
    }

    private fun prepare(
        apiResponse: List<ShipmentNetwork> = emptyList(),
        localResponse: List<List<ShipmentItemModel>>
    ) {
        coEvery { api.getShipments() } returns apiResponse

        coEvery { localData.fetch() } returnsMany localResponse
    }
}