package com.demodocebo.test.domain.usecases

import com.demodocebo.test.domain.base.BaseUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CheckStartUseCase @Inject constructor() : BaseUseCase<CheckStartUseCase.Params, CheckStartUseCase.Result>() {

    class Params



    override fun execute(params: Params) {
        Observable.just(true)
                .delay(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::success, ::error)
                .track()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          OUTPUT
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    sealed class Result {
        object OnSuccess : Result()
        object OnError : Result()
    }

    private fun success(status: Boolean) {
        liveData.value = Result.OnSuccess
    }

    private fun error(throwable: Throwable) {
        liveData.value = Result.OnError
    }
}