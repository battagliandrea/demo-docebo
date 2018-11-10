package com.demodocebo.test.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.demodocebo.test.domain.usecases.FetchRootUseCase
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
        private val fetchRootUseCase: FetchRootUseCase
) : ViewModel() {

    open val state = MediatorLiveData<CatalogViewModel.State>()

    init {
        state.addSource(fetchRootUseCase.getLiveData(), ::onFetchRootResult)
    }

    override fun onCleared() {
        fetchRootUseCase.cleanUp()
    }

    fun getState(): LiveData<State> = state

    fun fetchRoot() {
        state.value = State.ShowLoading
        fetchRootUseCase.execute()
    }

    private fun onFetchRootResult(result: FetchRootUseCase.Result?) {
        when (result) {
            is FetchRootUseCase.Result.OnSuccess -> {
                state.value = State.RootListLoaded(result.memes)
                state.value = State.ShowContent
            }
            is FetchRootUseCase.Result.OnError -> state.value = State.ShowError
        }
    }

    sealed class State {
        data class RootListLoaded(val roots: List<String?>) : State()
        object ShowLoading : State()
        object ShowContent : State()
        object ShowError : State()
    }
}