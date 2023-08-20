package pl.inpost.recruitmenttask.presentation.shipmentList.presenter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pl.inpost.recruitmenttask.presentation.shipmentList.domain.ArchiveShipmentUseCase
import pl.inpost.recruitmenttask.presentation.shipmentList.domain.FetchShipmentInfoUseCase
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentUiModel
import javax.inject.Inject

@HiltViewModel
internal class ShipmentListViewModel @Inject constructor(
    private val fetchShipmentInfoUseCase: FetchShipmentInfoUseCase,
    private val archiveShipmentUseCase: ArchiveShipmentUseCase,
) : ShipmentContract, ViewModel() {

    private val _viewState = MutableStateFlow(ShipmentUiModel())
    val viewState = _viewState.asStateFlow()

    init {
        refreshData(false)
    }

    override fun invokeAction(action: ShipmentAction) {
        when(action) {
            is ShipmentAction.Archive -> onArchive(action)
            ShipmentAction.Refresh -> onRefresh(action)
            ShipmentAction.InitialAction -> {
                // DO NOTHING FOR NOW, could be useful for impression
            }
        }
    }

    private fun onRefresh(action: ShipmentAction) {
        refreshData(true)
    }

    private fun onArchive(action: ShipmentAction.Archive) {
        viewModelScope.launch {
            archiveShipmentUseCase(action.id)
                .collectLatest {
                    _viewState.value = it.copy(
                        loading = false,
                    )
                }
        }
    }

    private fun refreshData(pullToRefresh: Boolean) {
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(
                loading = true,
            )

            fetchShipmentInfoUseCase(pullToRefresh)
                .collectLatest {
                    _viewState.value = it.copy(
                        loading = false,
                    )
                }
        }
    }
}
