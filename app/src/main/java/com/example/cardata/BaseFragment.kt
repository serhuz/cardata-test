package com.example.cardata

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mcxiaoke.koi.ext.getApp

abstract class BaseFragment : Fragment() {

    protected val disposer = Disposer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(disposer)
    }

    protected fun app() = getApp() as CarData

    protected fun showLoader() {
        (activity as BaseActivity).showLoader()
    }

    protected fun hideLoader() {
        (activity as BaseActivity).hideLoader()
    }

    protected inline fun <reified VM : ViewModel> activityViewModelProvider(crossinline factory: () -> ViewModelProvider.Factory) =
        lazy { ViewModelProviders.of(activity!!, factory()).get(VM::class.java) }

}
