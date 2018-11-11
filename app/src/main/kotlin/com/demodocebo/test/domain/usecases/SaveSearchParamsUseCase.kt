package com.demodocebo.test.domain.usecases

import com.demodocebo.test.data.repositories.CatalogRepository
import com.demodocebo.test.domain.base.BaseUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SaveSearchParamsUseCase @Inject constructor(
        private val repository: CatalogRepository
) : BaseUseCase<SaveSearchParamsUseCase.Params, SaveSearchParamsUseCase.Result>() {

    data class Params(val name: String, val type: String)

    override fun execute(params: Params) {
        repository.saveSearchParames(params.name, params.type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::success, ::error)
                .track()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          OUTPUT
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    sealed class Result {
        data class OnSuccess(val value: Boolean) : Result()
        object OnError : Result()
    }

    private fun success(value: Boolean) {
        liveData.value = Result.OnSuccess(value)
    }

    private fun error(throwable: Throwable) {
        liveData.value = Result.OnError
    }
}