package com.demodocebo.test.injection.components

import android.app.Application
import com.demodocebo.test.injection.binding.ActivityBindingModule
import com.demodocebo.test.injection.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by andrea on 26/08/2017.
 */

@Singleton
@Component(modules = [
    (AppModule::class),
    (ApiServiceModule::class),
    (DataModule::class),
    (NetworkModule::class),
    (ViewModelModule::class),
    (ActivityBindingModule::class),
    (AndroidSupportInjectionModule::class)
])

interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun dataModule(dataModule: DataModule): Builder

        fun build(): AppComponent
    }

}