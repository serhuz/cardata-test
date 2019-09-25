package com.example.cardata.main.di

import com.example.cardata.TokenReference
import com.example.cardata.main.MainActivityViewModel
import com.example.cardata.main.list.network.ListApi
import com.example.cardata.main.login.network.LoginApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class VmModule {

    @Provides
    @Singleton
    fun provideMainFactory(
        loginApi: LoginApi,
        listApi: ListApi,
        tokenReference: TokenReference
    ) = MainActivityViewModel.Factory(loginApi, listApi, tokenReference)
}
