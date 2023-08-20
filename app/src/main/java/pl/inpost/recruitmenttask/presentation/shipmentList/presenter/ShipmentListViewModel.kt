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
) : ShipmentContract, ViewModel() {

    private val _viewState = mutableStateOf(ShipmentUiModel())
    val viewState: State<ShipmentUiModel> = _viewState

    init {
        refreshData()
    }

    override fun invokeAction(action: ShipmentAction) {
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(
                loading = true,
            )

            fetchShipmentInfoUseCase()
                .collectLatest {
                    _viewState.value = it.copy(
                        loading = false,
                    )
                }
        }
    }
}
