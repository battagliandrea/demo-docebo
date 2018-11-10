package com.demodocebo.test.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.demodocebo.test.domain.usecases.CheckStartUseCase
import javax.inject.Inject

class SearchViewModel @Inject constructor(
        private val checkStartUseCase: CheckStartUseCase
) : ViewModel() {

    open var state = MediatorLiveData<State>()

    init {
        state.addSource(checkStartUseCase.getLiveData(), ::onCheckStartResult)
    }

    override fun onCleared() {
        checkStartUseCase.cleanUp()
    }

    fun getState(): LiveData<State> = state

    fun checkStart() {
        checkStartUseCase.execute()
    }

    private fun onCheckStartResult(result: CheckStartUseCase.Result?) {
        when (result) {
            is CheckStartUseCase.Result.OnSuccess -> {
                state.value = State.ShowHome
            }
            is CheckStartUseCase.Result.OnError -> state.value = State.ShowHome
        }
    }

    sealed class State {
        object ShowHome : State()
        object ShowError : State()
    }
}