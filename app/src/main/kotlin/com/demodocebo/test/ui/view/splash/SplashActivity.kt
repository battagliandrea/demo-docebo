package com.demodocebo.test.ui.view.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.demodocebo.test.ui.utils.observe
import com.demodocebo.test.ui.view.base.BaseActivity
import com.demodocebo.test.R
import com.demodocebo.test.ui.utils.getViewModel
import com.demodocebo.test.ui.view.home.HomeActivity
import com.demodocebo.test.ui.viewmodel.SplashViewModel

class SplashActivity(override val layoutResourceId: Int = R.layout.activity_view_splash ) : BaseActivity(){


    companion object {
        fun getCallingIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpViewModelStateObservers()
        getViewModel<SplashViewModel>(viewModelFactory).checkStart()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                                          INIT
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun setUpViewModelStateObservers() {
        observe(getViewModel<SplashViewModel>(viewModelFactory).getState()) { onStateChanged(it) }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                                          STATE
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun onStateChanged(state: SplashViewModel.State) = when (state) {
        SplashViewModel.State.ShowHome -> routeManager.launchShowList(this)
        SplashViewModel.State.ShowError -> routeManager.launchShowList(this)
    }
}
