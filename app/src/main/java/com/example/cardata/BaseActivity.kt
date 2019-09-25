package com.example.cardata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity : AppCompatActivity() {

    protected val disposer = Disposer()

    private var loader: LoaderView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(disposer)
    }

    fun showLoader() {
        if (loader == null) {
            loader = LoaderView(this)
        }
        loader!!.show()
    }

    fun hideLoader() {
        loader?.hide()
        loader = null
    }

    protected fun app() = application as CarData

    protected inline fun <reified VM : ViewModel> viewModelProvider(crossinline factory: () -> ViewModelProvider.Factory) =
        lazy { ViewModelProviders.of(this, factory()).get(VM::class.java) }

}
