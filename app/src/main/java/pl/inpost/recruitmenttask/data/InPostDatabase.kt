package pl.inpost.recruitmenttask.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.inpost.recruitmenttask.network.ApiTypeAdapter
import pl.inpost.recruitmenttask.features.shipmentList.data.ShipmentDao
import pl.inpost.recruitmenttask.features.shipmentList.data.model.ShipmentItemEntity

@Database(
    entities = [
        ShipmentItemEntity::class
    ],
    version = 1,
)
@TypeConverters(
    ApiTypeAdapter::class,
)
internal abstract class InPostDatabase : RoomDatabase() {

    abstract fun shipmentDao(): ShipmentDao
}