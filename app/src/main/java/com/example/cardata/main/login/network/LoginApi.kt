package com.example.cardata.main.login.network

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginApi(private val service: LoginService) {

    fun login(email: String, password: String): Observable<LoginResponse> =
        service.login(LoginBody(password, email))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
