package com.demodocebo.test.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.demodocebo.test.data.api.models.Item
import com.demodocebo.test.domain.usecases.GetCatalogItemsUseCase
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
                if(result.catalog.data.count >0){
                    state.value = State.ListLoaded(result.catalog.data.items)
                    state.value = State.CountLoaded(result.catalog.data.count)
                } else {
                    state.value = State.ShowEmptyStare
                }
            }
            is GetCatalogItemsUseCase.Result.OnError -> state.value = State.ShowError
        }
    }

    sealed class State {
        data class ListLoaded(val items: List<Item>) : State()
        data class CountLoaded(val count: Int) : State()
        object ShowLoading : State()
        object ShowEmptyStare : State()
        object ShowError : State()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //         USE CASE METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun fetchRoot() {
        state.value = State.ShowLoading
        getCatalogItemsUseCase.execute(GetCatalogItemsUseCase.Params())
    }
}