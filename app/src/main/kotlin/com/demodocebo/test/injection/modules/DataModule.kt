package com.demodocebo.test.injection.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by andrea on 26/08/2017.
 */
@Module
open class DataModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("andrea_dev-prefs", Context.MODE_PRIVATE)
    }
}