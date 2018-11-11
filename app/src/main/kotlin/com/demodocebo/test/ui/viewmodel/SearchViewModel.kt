package com.demodocebo.test.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.demodocebo.test.domain.usecases.GetCatalogItemsUseCase
import javax.inject.Inject

class SearchViewModel @Inject constructor(
        private val getCatalogItemsUseCase: GetCatalogItemsUseCase
) : ViewModel() {

    open var state = MediatorLiveData<State>()

    init {
        state.addSource(getCatalogItemsUseCase.getLiveData(), ::onFetchCatalogItemsResult)
    }

    override fun onCleared() {
        getCatalogItemsUseCase.cleanUp()
    }

    fun getState(): LiveData<State> = state

    fun fetchCatalogItems() {
        getCatalogItemsUseCase.execute()
    }

    private fun onFetchCatalogItemsResult(result: GetCatalogItemsUseCase.Result?) {
        when (result) {
            is GetCatalogItemsUseCase.Result.OnSuccess -> {
                state.value = State.Success
            }
            is GetCatalogItemsUseCase.Result.OnError -> state.value = State.Error
        }
    }

    sealed class State {
        object Success : State()
        object Error : State()
    }
}