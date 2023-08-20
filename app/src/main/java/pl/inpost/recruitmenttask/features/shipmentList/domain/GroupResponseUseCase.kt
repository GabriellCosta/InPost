package pl.inpost.recruitmenttask.features.shipmentList.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemModel
import pl.inpost.recruitmenttask.features.shipmentList.domain.mapper.ShipmentItemModelToUiModelMapper
import pl.inpost.recruitmenttask.features.shipmentList.presenter.model.ShipmentItemType
import pl.inpost.recruitmenttask.features.shipmentList.presenter.model.ShipmentUiModel
import pl.inpost.recruitmenttask.features.shipmentList.ui.ShipmentItemUIModel
import javax.inject.Inject

internal class GroupResponseUseCase @Inject constructor(
    private val mapper: ShipmentItemModelToUiModelMapper,
    private val sorter: SortShipmentItemsUseCase,
) {

    operator fun invoke(
        parameter: Flow<List<ShipmentItemModel>>
    ): Flow<ShipmentUiModel> {
        return parameter.map { toMap ->
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

        if (mapAndAddToList(mapped[true], mutableList)) {
            mutableList.add(
                0,
                ShipmentItemType.HeaderItem(
                    name = R.string.shipment_screen_item_separator_ready_to_pick_up,
                )
            )
        }

        mutableList.add(
            ShipmentItemType.HeaderItem(
                name = R.string.shipment_screen_item_separator_not_ready_to_pick_up,
            )
        )

        mapAndAddToList(mapped[false], mutableList)

        return mutableList
    }

    private fun mapAndAddToList(
        shipmentItemModels: List<ShipmentItemModel>?,
        mutableList: MutableList<ShipmentItemType>
    ): Boolean {
        val map = sorter(shipmentItemModels.orEmpty()).map { itemModel ->
            mapToUiModel(itemModel)
        }
        map.forEach {
            mutableList.add(ShipmentItemType.ShipmentItem(it))
        }

        return map.isNotEmpty()
    }

    private fun mapToUiModel(it: ShipmentItemModel): ShipmentItemUIModel {
        return mapper.mapFrom(it)
    }
}