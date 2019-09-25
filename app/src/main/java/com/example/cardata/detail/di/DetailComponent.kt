package com.example.cardata.detail.di

import com.example.cardata.detail.DetailActivity
import dagger.Subcomponent

@Subcomponent(modules = [DetailVmModule::class, DetailNetworkModule::class])
@DetailScope
interface DetailComponent {
    fun inject(detailActivity: DetailActivity)
}
