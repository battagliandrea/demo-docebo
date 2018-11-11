package com.demodocebo.test.domain.usecases

import com.demodocebo.test.data.api.models.Item
import com.demodocebo.test.data.repositories.CatalogRepository
import com.demodocebo.test.domain.base.BaseUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetCatalogItemsUseCase @Inject constructor(
        private val repository: CatalogRepository
) : BaseUseCase<GetCatalogItemsUseCase.Params, GetCatalogItemsUseCase.Result>() {

    class Params

    override fun execute(params: Params) {
        repository.fetchCatalogItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::success, ::error)
                .track()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          OUTPUT
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    sealed class Result {
        data class OnSuccess(val memes: List<Item>) : Result()
        object OnError : Result()
    }

    private fun success(items: List<Item>) {
        liveData.value = Result.OnSuccess(items)
    }

    private fun error(throwable: Throwable) {
        liveData.value = Result.OnError
    }
}