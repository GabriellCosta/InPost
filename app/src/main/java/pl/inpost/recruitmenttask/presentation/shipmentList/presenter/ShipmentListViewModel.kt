package pl.inpost.recruitmenttask.presentation.shipmentList.presenter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _viewState = mutableStateOf(ShipmentUiModel())
    val viewState: State<ShipmentUiModel> = _viewState

    init {
        refreshData()
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
        refreshData()
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
