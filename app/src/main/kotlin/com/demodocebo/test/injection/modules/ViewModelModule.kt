package com.demodocebo.test.injection.modules

import android.arch.lifecycle.ViewModel
import com.demodocebo.test.ui.viewmodel.HomeViewModel
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
    @ViewModelKey(HomeViewModel::class)
    fun provideShowListViewModel(viewModel: HomeViewModel): ViewModel = viewModel


}