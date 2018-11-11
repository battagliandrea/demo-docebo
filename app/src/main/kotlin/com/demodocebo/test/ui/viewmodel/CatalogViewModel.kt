package com.demodocebo.test.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.demodocebo.test.data.api.models.Item
import com.demodocebo.test.domain.usecases.GetCatalogItemsUseCase
import com.demodocebo.test.ui.view.catalog.SortType
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
        private val getCatalogItemsUseCase: GetCatalogItemsUseCase
) : ViewModel() {

    open val state = MediatorLiveData<CatalogViewModel.State>()

    init {
        state.addSource(getCatalogItemsUseCase.getLiveData(), ::onFetchRootResult)
    }

    override fun onCleared() {
        getCatalogItemsUseCase.cleanUp()
    }

    fun getState(): LiveData<State> = state

    private fun onFetchRootResult(result: GetCatalogItemsUseCase.Result?) {
        when (result) {
            is GetCatalogItemsUseCase.Result.OnSuccess -> {
                if(result.pair.first >0){
                    state.value = State.CountLoaded(result.pair.first)
                    state.value = State.ListLoaded(result.pair.second)
                } else {
                    state.value = State.ShowEmptyState
                }
            }
            is GetCatalogItemsUseCase.Result.OnError -> state.value = State.ShowError
        }
    }

    sealed class State {
        data class ListLoaded(val items: List<Item>) : State()
        data class CountLoaded(val count: Int) : State()
        object ShowLoading : State()
        object ShowLoadingMore : State()
        object ShowEmptyState : State()
        object ShowError : State()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //         USE CASE METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun fetchCatalog(loadMore: Boolean, sortType: SortType) {
        if(loadMore){
            state.value = State.ShowLoadingMore
        } else {
            state.value = State.ShowLoading
        }
        getCatalogItemsUseCase.execute(GetCatalogItemsUseCase.Params(sortType = sortType))
    }
}