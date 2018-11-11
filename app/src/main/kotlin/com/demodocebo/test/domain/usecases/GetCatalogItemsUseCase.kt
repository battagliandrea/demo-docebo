package com.demodocebo.test.domain.usecases

import com.demodocebo.test.data.api.models.Item
import com.demodocebo.test.data.repositories.CatalogRepository
import com.demodocebo.test.domain.base.BaseUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetCatalogItemsUseCase @Inject constructor(
        private val repository: CatalogRepository
) : BaseUseCase<GetCatalogItemsUseCase.Result>() {

    sealed class Result {
        data class OnSuccess(val memes: List<Item>) : Result()
        object OnError : Result()
    }

    override fun execute() {
        repository.fetchCatalogItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::success, ::error)
                .track()
    }

    private fun success(items: List<Item>) {
        liveData.value = Result.OnSuccess(items)
    }

    private fun error(throwable: Throwable) {
        liveData.value = Result.OnError
    }
}