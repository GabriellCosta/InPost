package pl.inpost.recruitmenttask.features.shipmentList.data

import androidx.room.Entity

@Entity
class ShipmentItemUpdateEntity(
    val number: String,
    val isArchived: Boolean,
)