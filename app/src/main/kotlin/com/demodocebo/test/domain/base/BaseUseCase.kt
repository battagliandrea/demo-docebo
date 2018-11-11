package com.demodocebo.test.domain.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseUseCase<Input, Output>(
        private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
        protected val liveData: MutableLiveData<Output> = MutableLiveData()
) : UseCase<Input, Output> {

    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    override fun getLiveData(): LiveData<Output> = liveData

    override fun cleanUp() {
        compositeDisposable.clear()
    }
}
