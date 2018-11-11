package com.demodocebo.test.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.demodocebo.test.domain.usecases.GetCatalogItemsUseCase
import com.demodocebo.test.domain.usecases.SaveSearchParamsUseCase
import javax.inject.Inject

class SearchViewModel @Inject constructor(
        private val saveSearchParamsUseCase: SaveSearchParamsUseCase
) : ViewModel() {

    open var state = MediatorLiveData<State>()

    init {
        state.addSource(saveSearchParamsUseCase.getLiveData(), ::onSaveSearchParamsResult)
    }

    override fun onCleared() {
        saveSearchParamsUseCase.cleanUp()
    }

    fun getState(): LiveData<State> = state

    private fun onSaveSearchParamsResult(result: SaveSearchParamsUseCase.Result?) {
        when (result) {
            is SaveSearchParamsUseCase.Result.OnSuccess -> state.value = State.Success
            is SaveSearchParamsUseCase.Result.OnError -> state.value = State.Error
        }
    }

    sealed class State {
        object Success : State()
        object Error : State()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //         USE CASE METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun saveParams(name: String, type: String) {
        saveSearchParamsUseCase.execute(SaveSearchParamsUseCase.Params(name = name, type = type))
    }
}