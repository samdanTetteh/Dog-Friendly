package com.ijikod.dog_friendly.common

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observables.ConnectableObservable


/**
 * BaseViewModel class that implements the generic reactive view state <S> store.
 * It supports persistence of view state across orientation changes.
 * It also supports view events <E> that can be used as one-off events such as navigation, toasts, snackbars etc.
 * Its a view state that is multi-cast observable that provides the same data to multiple observers.
 */
open class RxViewModelStore<S, E>(initState: S) : ViewModel() {

    private val reducers = PublishRelay.create<Reducer<S>>().toSerialized() // making this relay thread safe in order to be cable to updated by any worker thread and improve UI performance
    private val events = PublishRelay.create<E>()
    private val compositeDisposable = CompositeDisposable()

    private val store: ConnectableObservable<S> = reducers
        .scan(initState) { oldState, reducer -> reducer.reduce(oldState) }
        .replay(1)
        .apply { connect() }

    protected fun applyState(reducer: Reducer<S>) = reducers.accept(reducer)
    protected fun publish(e: E) = events.accept(e)
    protected fun Disposable.addDisposable() = compositeDisposable.add(this)

    fun states(): Observable<S> = store.observeOn(AndroidSchedulers.mainThread())
    fun events(): Observable<E> = events.observeOn(AndroidSchedulers.mainThread())

    fun currentState(): S = store.blockingFirst()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

interface Reducer<S> {
    fun reduce(oldState: S): S

    companion object {
        inline operator fun <S> invoke(crossinline op: (s: S) -> S) =
            object : Reducer<S> {
                override fun reduce(oldState: S) = op(oldState)
            }
    }
}