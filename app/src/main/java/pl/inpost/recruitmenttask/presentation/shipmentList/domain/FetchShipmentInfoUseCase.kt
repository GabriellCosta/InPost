package pl.inpost.recruitmenttask.presentation.shipmentList.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.presentation.shipmentList.data.ShipmentItemModel
import pl.inpost.recruitmenttask.presentation.shipmentList.data.ShipmentRepository
import pl.inpost.recruitmenttask.presentation.shipmentList.domain.mapper.ShipmentItemModelToUiModelMapper
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentItemType
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentUiModel
import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ShipmentItemUIModel
import javax.inject.Inject

internal class FetchShipmentInfoUseCase @Inject constructor(
    private val repository: ShipmentRepository,
    private val mapper: ShipmentItemModelToUiModelMapper,
    private val sorter: SortShipmentItemsUseCase,
) {

    suspend operator fun invoke(): Flow<ShipmentUiModel> {

        return repository.fetchShipments()
            .map { toMap ->
                if (toMap.isNotEmpty()) {
                    ShipmentUiModel(
                        empty = false,
                        items = processResponse(toMap)
                    )
                } else {
                    ShipmentUiModel(
                        empty = true
                    )
                }
            }
    }

    private fun processResponse(toMap: List<ShipmentItemModel>): List<ShipmentItemType> {

        val mutableList = mutableListOf<ShipmentItemType>()

        val mapped = toMap
            .groupBy { it.operationModel.highlight }

        sorter(mapped[true].orEmpty()).map { itemModel ->
            mapToUiModel(itemModel)
        }.forEach {
            mutableList.add(ShipmentItemType.ShipmentItem(it))
        }

        mutableList.add(
            0,
            ShipmentItemType.HeaderItem(
                name = R.string.shipment_screen_item_separator_ready_to_pick_up,
            )
        )

        mutableList.add(
            ShipmentItemType.HeaderItem(
                name = R.string.shipment_screen_item_separator_not_ready_to_pick_up,
            )
        )

        sorter(mapped[false].orEmpty()).map { itemModel ->
            mapToUiModel(itemModel)
        }.forEach {
            mutableList.add(ShipmentItemType.ShipmentItem(it))
        }

        return mutableList
    }

    private fun mapToUiModel(it: ShipmentItemModel): ShipmentItemUIModel {
        return mapper.mapFrom(it)
    }
}