package pl.inpost.recruitmenttask.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.inpost.recruitmenttask.data.InPostDatabase
import pl.inpost.recruitmenttask.features.shipmentList.data.ShipmentDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesDb(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        InPostDatabase::class.java,
        "InPostDatabase"
    )
        .build()

    @Provides
    fun provideChannelDao(database: InPostDatabase): ShipmentDao =
        database.shipmentDao()
}