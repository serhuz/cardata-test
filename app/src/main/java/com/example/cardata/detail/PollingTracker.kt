package com.example.cardata.detail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class PollingTracker(
    private val resumeAction: () -> Unit,
    private val pauseAction: () -> Unit
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        resumeAction.invoke()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        pauseAction.invoke()
    }
}
