package pl.inpost.recruitmenttask.features.shipmentList.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import pl.inpost.recruitmenttask.features.shipmentList.data.fetch.FetchDecorators
import pl.inpost.recruitmenttask.features.shipmentList.data.fetch.NonArchivedDecorator
import pl.inpost.recruitmenttask.features.shipmentList.data.fetch.ValidStatusDecorator
import pl.inpost.recruitmenttask.features.shipmentList.data.fetch.ValidTypeDecorator

@Module
@InstallIn(SingletonComponent::class)
internal interface ShipmentDataModule {

    @Binds
    @IntoSet
    fun provideNonArchived(bind: NonArchivedDecorator): FetchDecorators

    @Binds
    @IntoSet
    fun provideValidStatus(bind: ValidStatusDecorator): FetchDecorators

    @Binds
    @IntoSet
    fun provideValidType(bind: ValidTypeDecorator): FetchDecorators

}