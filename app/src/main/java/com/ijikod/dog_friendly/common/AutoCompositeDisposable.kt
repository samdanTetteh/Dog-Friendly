package com.ijikod.dog_friendly.common

import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class AutoCompositeDisposable(private val lifecycle: Lifecycle,
                              private val doOnPause: Boolean = false) :
    DefaultLifecycleObserver {

    private val compositeDisposable: CompositeDisposable

    init {
        lifecycle.addObserver(this)
        compositeDisposable = CompositeDisposable()
    }

    fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        if (doOnPause) {
            compositeDisposable.clear()
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        if (!doOnPause) {
            compositeDisposable.clear()
        }
        lifecycle.removeObserver(this)
    }
}

fun Disposable.addTo(d: AutoCompositeDisposable) {
    d.add(this)
}