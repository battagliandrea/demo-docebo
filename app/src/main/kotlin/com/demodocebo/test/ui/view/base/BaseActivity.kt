package com.demodocebo.test.ui.view.base

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import com.demodocebo.test.route.RouteManager
import com.demodocebo.test.ui.utils.manageTheme
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var routeManager: RouteManager
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {

        this.manageTheme()

        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        setContentView(layoutResourceId)
    }

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}