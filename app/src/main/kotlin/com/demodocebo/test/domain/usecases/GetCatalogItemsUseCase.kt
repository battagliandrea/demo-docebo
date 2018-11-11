package com.demodocebo.test.domain.usecases

import com.demodocebo.test.data.api.models.Item
import com.demodocebo.test.data.repositories.CatalogRepository
import com.demodocebo.test.domain.base.BaseUseCase
import com.demodocebo.test.ui.view.catalog.SortType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetCatalogItemsUseCase @Inject constructor(
        private val repository: CatalogRepository
) : BaseUseCase<GetCatalogItemsUseCase.Params, GetCatalogItemsUseCase.Result>() {

    data class Params(val sortType: SortType)

    override fun execute(params: Params) {
        repository.fetchCatalogItems(params.sortType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::success, ::error)
                .track()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          OUTPUT
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    sealed class Result {
        data class OnSuccess(val pair: Pair<Int, List<Item>>) : Result()
        object OnError : Result()
    }

    private fun success(pair: Pair<Int, List<Item>>) {
        liveData.value = Result.OnSuccess(pair)
    }

    private fun error(throwable: Throwable) {
        liveData.value = Result.OnError
    }
}