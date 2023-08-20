package pl.inpost.recruitmenttask.presentation.shipmentList.domain

import kotlinx.coroutines.flow.Flow
import pl.inpost.recruitmenttask.presentation.shipmentList.data.ShipmentRepository
import pl.inpost.recruitmenttask.presentation.shipmentList.presenter.model.ShipmentUiModel
import javax.inject.Inject

internal class ArchiveShipmentUseCase @Inject constructor(
    private val repository: ShipmentRepository,
    private val group: GroupResponseUseCase,
) {

    suspend operator fun invoke(number: String): Flow<ShipmentUiModel> {
        return group(repository.archiveShipment(number))
    }
}