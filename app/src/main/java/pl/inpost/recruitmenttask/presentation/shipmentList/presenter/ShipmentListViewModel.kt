package pl.inpost.recruitmenttask.presentation.shipmentList.presenter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.network.api.ShipmentApi
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentItemType
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentUiModel
import pl.inpost.recruitmenttask.presentation.shipmentList.ui.ShipmentItemUIModel
import javax.inject.Inject

@HiltViewModel
class ShipmentListViewModel @Inject constructor(
    private val shipmentApi: ShipmentApi
) : ViewModel() {

    private val mutableViewState = mutableStateOf(ShipmentUiModel())
    val viewState: State<ShipmentUiModel> = mutableViewState

    init {
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            val shipments = shipmentApi.getShipments()

            val mutableList = mutableListOf<ShipmentItemType>()

            val mapped = shipments
                .groupBy { it.operations.highlight }

            mutableList.add(
                ShipmentItemType.HeaderItem(
                    name = "Gotowe do odbioru",
                )
            )

            mapped[true]?.map {
                ShipmentItemUIModel(
                    number = it.number,
                    status = it.status,
                    contact = it.sender?.email.orEmpty(),
                    detail = null,
                    icon = R.drawable.ic_kurier,
                )
            }.orEmpty()
                .forEach {
                    mutableList.add(ShipmentItemType.ShipmentItem(it))
                }

            mutableList.add(
                ShipmentItemType.HeaderItem(
                    name = "Pozostałe",
                )
            )

            mapped[false]?.map {
                ShipmentItemUIModel(
                    number = it.number,
                    status = it.status,
                    contact = it.sender?.email.orEmpty(),
                    detail = null,
                    icon = R.drawable.ic_kurier,
                )
            }.orEmpty()
                .forEach {
                    mutableList.add(ShipmentItemType.ShipmentItem(it))
                }

            mutableViewState.value = ShipmentUiModel(mutableList)
        }
    }
}
