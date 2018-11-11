package com.demodocebo.test.domain.base

import android.arch.lifecycle.LiveData

interface UseCase<Input, Output> {

    fun getLiveData(): LiveData<Output>

    fun cleanUp()

    fun execute(params: Input)
}
