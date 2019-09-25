package com.example.cardata.detail.di

import com.example.cardata.detail.DetailActivityViewModel
import com.example.cardata.detail.network.DetailApi
import dagger.Module
import dagger.Provides

@Module
class DetailVmModule {

    @Provides
    @DetailScope
    fun provideDetailFactory(detailApi: DetailApi) = DetailActivityViewModel.Factory(detailApi)
}
