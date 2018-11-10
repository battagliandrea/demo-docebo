package com.demodocebo.test.injection.modules

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.demodocebo.test.scheduler.AppRxSchedulers
import com.demodocebo.test.scheduler.RxSchedulers
import com.demodocebo.test.ui.base.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by andrea on 26/08/2017.
 */

@Module
open class AppModule {

    @Provides
    @Singleton
    open fun provideApplicationContext(app: Application): Context = app

    @Provides
    @Singleton
    open fun provideSchedulerProvider(): RxSchedulers = AppRxSchedulers()

    @Provides
    @Singleton
    open fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory
}