package com.example.navigationtest

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

// Bag para dar dispose nos meus subscribes!
interface DisposableHolder {
    val disposables: CompositeDisposable

    fun disposeAll()
}

class DisposableHolderDelegate @Inject constructor() : DisposableHolder {
    override val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    override fun disposeAll() {
        disposables.clear()
    }
}