package com.demodocebo.test.ui.view.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.demodocebo.test.R
import com.demodocebo.test.ui.utils.getViewModel
import com.demodocebo.test.ui.utils.manageFullScreenTheme
import com.demodocebo.test.ui.utils.observe
import com.demodocebo.test.ui.view.base.BaseActivity
import com.demodocebo.test.ui.view.catalog.CatalogActivity
import com.demodocebo.test.ui.viewmodel.SplashViewModel

class SplashActivity(override val layoutResourceId: Int = R.layout.activity_splash ) : BaseActivity(){


    companion object {
        fun getCallingIntent(context: Context) = Intent(context, CatalogActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        manageFullScreenTheme()
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
        SplashViewModel.State.ShowHome -> {
            routeManager.launchSearch(this)
            finish()
        }
        SplashViewModel.State.ShowError -> {
            Toast.makeText(this, getString(R.string.error_undefined), Toast.LENGTH_SHORT).show()
        }
    }
}
