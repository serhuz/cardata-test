package com.example.cardata.detail.network

import io.reactivex.Observable
import retrofit2.http.GET

interface DetailService {

    @GET("/cars/states")
    fun getCarsStates(): Observable<List<CarState>>
}
