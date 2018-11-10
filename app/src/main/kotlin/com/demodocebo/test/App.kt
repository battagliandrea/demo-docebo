package com.demodocebo.test

import com.demodocebo.test.injection.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by andrea on 26/08/2017.
 */
class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<DaggerApplication> =
            DaggerAppComponent.builder()
                    .application(this)
                    .build()

    override fun onCreate() {
        super.onCreate()

    }
}