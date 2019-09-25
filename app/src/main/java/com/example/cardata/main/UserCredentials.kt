package com.example.cardata.main

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import com.example.cardata.BR
import java.util.regex.Pattern

class UserCredentials : BaseObservable() {

    private val emailPattern =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)!!

    @get:Bindable
    var email = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var emailStatus = FieldStatus.EMPTY
        set(value) {
            field = value
            notifyPropertyChanged(BR.emailStatus)
        }

    @get:Bindable
    var password = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    @get:Bindable
    var passwordStatus = FieldStatus.EMPTY
        set(value) {
            field = value
            notifyPropertyChanged(BR.passwordStatus)
        }

    init {
        addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when (propertyId) {
                    BR.email -> emailStatus = checkEmailStatus(email)
                    BR.password -> passwordStatus = checkPasswordStatus(password)
                }
            }
        })
    }

    fun setEmptyToInvalid() {
        if (emailStatus == FieldStatus.EMPTY) {
            emailStatus = FieldStatus.INVALID
        }

        if (passwordStatus == FieldStatus.EMPTY) {
            passwordStatus = FieldStatus.INVALID
        }
    }

    fun isValid() = emailStatus == FieldStatus.VALID && passwordStatus == FieldStatus.VALID

    fun checkEmailStatus(email: String?) =
        when {
            email.isNullOrBlank() -> FieldStatus.EMPTY
            emailPattern.matcher(email).matches() -> FieldStatus.VALID
            else -> FieldStatus.INVALID
        }

    fun checkPasswordStatus(password: String?) =
        when {
            password.isNullOrBlank() -> FieldStatus.EMPTY
            password.length < MIN_PASSWORD_LENGTH -> FieldStatus.INVALID
            else -> FieldStatus.VALID
        }

    companion object {
        const val MIN_PASSWORD_LENGTH =
            7 // task mentioned 8 symbols, but provided password actually has 7 symbols
    }
}
