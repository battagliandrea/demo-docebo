package com.demodocebo.test.injection.modules

import android.arch.lifecycle.ViewModel
import com.demodocebo.test.ui.viewmodel.CatalogViewModel
import com.demodocebo.test.ui.viewmodel.SearchViewModel
import com.demodocebo.test.ui.viewmodel.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun provideSplashViewModel(viewModel: SplashViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun provideSearchViewModel(viewModel: SearchViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(CatalogViewModel::class)
    fun provideCatalogViewModel(viewModel: CatalogViewModel): ViewModel = viewModel


}