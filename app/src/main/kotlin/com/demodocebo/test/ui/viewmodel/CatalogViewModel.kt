package com.demodocebo.test.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.demodocebo.test.domain.usecases.GetCatalogItemsUseCase
import com.demodocebo.test.domain.usecases.SaveSearchParamsUseCase
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
                state.value = State.ShowContent
            }
            is GetCatalogItemsUseCase.Result.OnError -> state.value = State.ShowError
        }
    }

    sealed class State {
        object ShowLoading : State()
        object ShowContent : State()
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