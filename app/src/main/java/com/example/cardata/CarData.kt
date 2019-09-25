package com.example.cardata

import android.app.Application
import com.example.cardata.detail.di.DetailComponent
import com.example.cardata.main.di.AppComponent
import com.example.cardata.main.di.DaggerAppComponent

class CarData : Application() {

    lateinit var appComponent: AppComponent

    var detailComponent: DetailComponent? = null
        get() {
            if (field == null) {
                field = appComponent.detailComponent()
            }
            return field
        }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
