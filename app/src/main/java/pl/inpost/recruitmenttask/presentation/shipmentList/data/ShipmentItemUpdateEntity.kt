package pl.inpost.recruitmenttask.presentation.shipmentList.data

import androidx.room.Entity

@Entity
class ShipmentItemUpdateEntity(
    val number: String,
    val isArchived: Boolean,
)