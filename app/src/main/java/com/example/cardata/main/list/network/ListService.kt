package com.example.cardata.main.list.network

import io.reactivex.Observable
import retrofit2.http.GET

interface ListService {

    @GET("/cars")
    fun getCars(): Observable<List<CarInfo>>
}
