package pl.inpost.recruitmenttask.presentation.shipmentList.domain

import android.util.Log
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.network.api.ShipmentApi
import pl.inpost.recruitmenttask.network.model.ShipmentNetwork
import pl.inpost.recruitmenttask.presentation.shipmentList.domain.mapper.StatusToResourceMapper
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentItemType
import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ShipmentItemUIModel
import javax.inject.Inject

class FetchShipmentInfoUseCase @Inject constructor(
    private val shipmentApi: ShipmentApi,
    private val mapper: StatusToResourceMapper,
) {

    suspend operator fun invoke(): List<ShipmentItemType> {

        val shipments = shipmentApi.getShipments()

        val mutableList = mutableListOf<ShipmentItemType>()

        val mapped = shipments
            .groupBy { it.operations.highlight }

        mutableList.add(
            ShipmentItemType.HeaderItem(
                name = R.string.shipment_screen_item_separator_ready_to_pick_up,
            )
        )

        mapped[true]?.mapNotNull {
            mapToUiModel(it)
        }.orEmpty()
            .forEach {
                mutableList.add(ShipmentItemType.ShipmentItem(it))
            }

        mutableList.add(
            ShipmentItemType.HeaderItem(
                name = R.string.shipment_screen_item_separator_not_ready_to_pick_up,
            )
        )

        mapped[false]?.mapNotNull {
            mapToUiModel(it)
        }.orEmpty()
            .forEach {
                mutableList.add(ShipmentItemType.ShipmentItem(it))
            }

        return mutableList
    }

    private fun mapToUiModel(it: ShipmentNetwork): ShipmentItemUIModel? {
        return try {
            ShipmentItemUIModel(
                number = it.number,
                status = mapper.mapFrom(it.status),
                contact = it.sender?.email.orEmpty(),
                detail = null,
                icon = R.drawable.ic_kurier,
            )
        } catch (ex: IllegalArgumentException) {
            Log.d("ViewModel", ex.localizedMessage.orEmpty())
            null
        }
    }
}