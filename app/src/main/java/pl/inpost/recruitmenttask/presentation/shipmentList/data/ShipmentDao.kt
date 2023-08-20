package pl.inpost.recruitmenttask.presentation.shipmentList.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
internal interface ShipmentDao {

    @Query("SELECT * FROM shipmentitementity")
    suspend fun fetchShipment(): List<ShipmentItemEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(shipment: List<ShipmentItemEntity>) : List<Long>

    @Update(ShipmentItemEntity::class)
    suspend fun updateShipment(shipment: ShipmentItemUpdateEntity)
}