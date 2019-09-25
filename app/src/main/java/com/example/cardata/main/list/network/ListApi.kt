package com.example.cardata.main.list.network

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListApi(private val service: ListService) {

    fun getCars(): Observable<List<CarInfo>> = service.getCars()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
