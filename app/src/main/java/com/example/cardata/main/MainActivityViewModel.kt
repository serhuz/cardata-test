package com.example.cardata.main

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardata.SingleLiveEvent
import com.example.cardata.TokenReference
import com.example.cardata.VoidLiveEvent
import com.example.cardata.main.list.CarListAdapter
import com.example.cardata.main.list.network.CarInfo
import com.example.cardata.main.list.network.ListApi
import com.example.cardata.main.login.network.LoginApi

class MainActivityViewModel(
    private val loginApi: LoginApi,
    private val listApi: ListApi,
    private val tokenReference: TokenReference
) : ViewModel() {

    val loginClick = VoidLiveEvent()
    val loginSuccess = VoidLiveEvent()
    val carInfoClick = SingleLiveEvent<CarInfo>()

    val credentials = UserCredentials()
    val carListAdapter = ObservableField(CarListAdapter(carInfoClick))

    fun performLogin() {
        credentials.setEmptyToInvalid()

        if (credentials.isValid()) {
            loginClick.call()
        }
    }

    fun login() = loginApi.login(credentials.email, credentials.password)

    fun loadCars() = listApi.getCars()

    fun proceedToCarList(token: String) {
        tokenReference.set(token)
        loginSuccess.call()
    }

    fun clearCredentials() {
        credentials.apply {
            email = ""
            password = ""
        }
    }

    fun updateCarList(list: List<CarInfo>) {
        carListAdapter.get()!!.carList = list
    }

    class Factory(
        private val loginApi: LoginApi,
        private val listApi: ListApi,
        private val tokenReference: TokenReference
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            MainActivityViewModel(loginApi, listApi, tokenReference) as T
    }
}
