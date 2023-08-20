package pl.inpost.recruitmenttask.presentation.shipmentList.presenter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import pl.inpost.recruitmenttask.network.api.ShipmentApi
import pl.inpost.recruitmenttask.network.model.ShipmentNetwork
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentItemUiModel
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentUiModel
import pl.inpost.recruitmenttask.util.setState
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

            shipments.map {
                ShipmentItemUiModel(
                    number = it.number,
                    status = it.status,
                    contact = it.sender?.email.orEmpty(),
                )
            }

            mutableViewState.value {  }
        }
    }
}
