package pl.inpost.recruitmenttask.features.shipmentList.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity
internal data class ShipmentItemEntity(
    @PrimaryKey(false)
    @ColumnInfo("number")
    val number: String,
    @ColumnInfo("status")
    val status: String,
    @ColumnInfo("shipmentType")
    val shipmentType: String,
    @ColumnInfo("expiryDate")
    val expiryDate: ZonedDateTime?,
    @ColumnInfo("storedDate")
    val storedDate: ZonedDateTime?,
    @ColumnInfo("pickUpDate")
    val pickUpDate: ZonedDateTime?,
    val email: String?,
    val phoneNumber: String?,
    val name: String?,
    val manualArchive: Boolean,
    val delete: Boolean,
    val collect: Boolean,
    val highlight: Boolean,
    val expandAvizo: Boolean,
    val endOfWeekCollection: Boolean,
    val isArchived: Boolean = false,
)