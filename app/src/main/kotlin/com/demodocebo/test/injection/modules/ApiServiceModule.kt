package com.demodocebo.test.injection.modules

import android.content.Context
import com.demodocebo.test.BuildConfig
import com.demodocebo.test.R
import com.demodocebo.test.data.api.DoceboDemoApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by andrea on 27/08/2017.
 */
@Module
class ApiServiceModule {

    @Provides
    @Singleton
    internal fun provideApi(context: Context, httpClient: OkHttpClient, rxAdapter: RxJava2CallAdapterFactory, gsonConverterFactory: GsonConverterFactory): DoceboDemoApi {

        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }

        val retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.baseurl))
                .client(httpClient)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(gsonConverterFactory)
                .build()

        return retrofit.create(DoceboDemoApi::class.java)
    }
}