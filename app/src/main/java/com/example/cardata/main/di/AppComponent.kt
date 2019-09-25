package com.example.cardata.main.di

import com.example.cardata.detail.di.DetailComponent
import com.example.cardata.main.MainActivity
import com.example.cardata.main.list.CarListFragment
import com.example.cardata.main.login.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [VmModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(loginFragment: LoginFragment)
    fun inject(carListFragment: CarListFragment)
    fun detailComponent(): DetailComponent
}
