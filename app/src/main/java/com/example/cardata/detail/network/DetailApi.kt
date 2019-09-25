package com.example.cardata.detail.network

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DetailApi(private val service: DetailService) {

    fun pollState(imei: String, shouldUpdate: () -> Boolean) =
        Observable.interval(2, TimeUnit.SECONDS)
            .filter { shouldUpdate.invoke() }
            .flatMap { service.getCarsStates() }
            .flatMap { Observable.fromIterable(it) }
            .filter { it.imei == imei }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
