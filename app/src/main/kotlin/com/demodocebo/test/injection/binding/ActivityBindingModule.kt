package com.demodocebo.test.injection.binding

import com.demodocebo.test.injection.scopes.ActivityScope
import com.demodocebo.test.ui.view.catalog.CatalogActivity
import com.demodocebo.test.ui.view.search.SearchActivity
import com.demodocebo.test.ui.view.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun searchActivity(): SearchActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun catalogActivity(): CatalogActivity
}