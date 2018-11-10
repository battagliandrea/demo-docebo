package com.demodocebo.test.domain.base

import android.arch.lifecycle.LiveData

interface UseCase<T> {

    fun getLiveData(): LiveData<T>

    fun cleanUp()

    fun execute()
}
