package com.example.cardata.detail

import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardata.detail.network.CarState
import com.example.cardata.detail.network.DetailApi
import java.util.concurrent.atomic.AtomicBoolean

class DetailActivityViewModel(private val detailApi: DetailApi) : ViewModel() {

    var shouldUpdate = AtomicBoolean(false)

    val state = ObservableField("")
    val fuelLevelLiters = ObservableDouble()
    val fuelLevelPercents = ObservableInt()

    fun pollState(imei: String) = detailApi.pollState(imei) { shouldUpdate.get() }

    fun updateCarState(state: CarState) {
        this.state.set(state.state)
        fuelLevelLiters.set(state.fuelLevelLiters)
        fuelLevelPercents.set(state.fuelLevelPercentage)
    }

    class Factory(private val detailApi: DetailApi) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            DetailActivityViewModel(detailApi) as T
    }
}
