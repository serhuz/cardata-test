package com.example.cardata.main.login.network

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/auth/login")
    fun login(@Body body: LoginBody): Observable<LoginResponse>
}
