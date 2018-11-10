package com.demodocebo.test.data.api

import com.demodocebo.test.data.api.exceptions.ApiException
import com.demodocebo.test.data.api.exceptions.NetworkException
import io.reactivex.Observable
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

fun <T> Observable<T>.onErrorCheckRetrofitException(): Observable<T> = onErrorResumeNext { t: Throwable ->

    when (t) {
        is HttpException -> Observable.error<T>(ApiException.create(
                t.message(), t, t.code(), String().apply { t.response().errorBody()?.string() }))

        is SocketTimeoutException -> Observable.error<T>(NetworkException.timeout(t))
        is IOException -> Observable.error<T>(NetworkException.noNetwork(t))
        else -> Observable.error<T>(NetworkException.generic(t))
    }

}