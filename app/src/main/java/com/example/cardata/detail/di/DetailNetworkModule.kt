package com.example.cardata.detail.di

import com.example.cardata.detail.network.DetailApi
import com.example.cardata.detail.network.DetailService
import com.example.cardata.main.di.AuthRetrofit
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DetailNetworkModule {

    @Provides
    @DetailScope
    fun provideDetailApi(@AuthRetrofit retrofit: Retrofit) =
        DetailApi(retrofit.create(DetailService::class.java))
}
