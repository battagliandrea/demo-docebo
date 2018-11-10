package com.demodocebo.test.ui.view.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.demodocebo.test.ui.utils.observe
import com.demodocebo.test.ui.view.base.BaseActivity
import com.demodocebo.test.R
import com.demodocebo.test.ui.utils.getViewModel
import com.demodocebo.test.ui.viewmodel.HomeViewModel

class HomeActivity(override val layoutResourceId: Int = R.layout.activity_home) : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpViewModelStateObservers()
        getViewModel<HomeViewModel>(viewModelFactory).fetchRoot()
    }

    private fun setUpViewModelStateObservers() {
        observe(getViewModel<HomeViewModel>(viewModelFactory).getState()) { onStateChanged(it) }
    }

    private fun onStateChanged(state: HomeViewModel.State) = when (state) {
        is HomeViewModel.State.RootListLoaded -> Toast.makeText(this, "LOADED", Toast.LENGTH_SHORT).show()
        HomeViewModel.State.ShowLoading -> Toast.makeText(this, "LOADING", Toast.LENGTH_SHORT).show()
        HomeViewModel.State.ShowContent -> Toast.makeText(this, "CONTENT", Toast.LENGTH_SHORT).show()
        HomeViewModel.State.ShowError -> Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun getCallingIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

}