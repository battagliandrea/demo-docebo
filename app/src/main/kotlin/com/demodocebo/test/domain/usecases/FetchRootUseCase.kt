package com.demodocebo.test.domain.usecases

import com.demodocebo.test.data.repositories.RootRepository
import com.demodocebo.test.domain.base.BaseUseCase
import javax.inject.Inject

class FetchRootUseCase @Inject constructor(
        private val repository: RootRepository
) : BaseUseCase<FetchRootUseCase.Result>() {

    sealed class Result {
        data class OnSuccess(val memes: List<String?>) : Result()
        object OnError : Result()
    }

    override fun execute() {
//        repository.fetchRootList()
//                //.map(mapper::map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(::success, ::error)
//                .track()
    }

    private fun success(memes: List<String?>) {
        liveData.value = Result.OnSuccess(memes)
    }

    private fun error(throwable: Throwable) {
        liveData.value = Result.OnError
    }
}