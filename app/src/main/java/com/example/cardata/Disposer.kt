package com.example.cardata

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class Disposer : LifecycleObserver {

    private val onDestroyDisposables = CompositeDisposable()

    fun disposeOnDestroy(disposable: Disposable) {
        onDestroyDisposables.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        onDestroyDisposables.dispose()
    }
}
