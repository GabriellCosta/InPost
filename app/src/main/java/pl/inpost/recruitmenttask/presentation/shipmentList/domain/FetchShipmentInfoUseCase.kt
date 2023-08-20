package pl.inpost.recruitmenttask.presentation.shipmentList.domain

import kotlinx.coroutines.flow.Flow
import pl.inpost.recruitmenttask.presentation.shipmentList.data.ShipmentRepository
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentUiModel
import javax.inject.Inject

internal class FetchShipmentInfoUseCase @Inject constructor(
    private val repository: ShipmentRepository,
    private val group: GroupResponseUseCase,
) {

    suspend operator fun invoke(refresh: Boolean): Flow<ShipmentUiModel> {
        return group(repository.fetchShipments(refresh))
    }
}