package pl.inpost.recruitmenttask.presentation.shipmentList.presenter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pl.inpost.recruitmenttask.presentation.shipmentList.domain.FetchShipmentInfoUseCase
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentUiModel
import javax.inject.Inject

@HiltViewModel
internal class ShipmentListViewModel @Inject constructor(
    private val fetchShipmentInfoUseCase: FetchShipmentInfoUseCase,
) : ViewModel() {

    private val mutableViewState = mutableStateOf(ShipmentUiModel())
    val viewState: State<ShipmentUiModel> = mutableViewState

    init {
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            fetchShipmentInfoUseCase()
                .collectLatest {
                    mutableViewState.value = ShipmentUiModel(it)
                }
        }
    }
}
