package com.demodocebo.test.data.api

import com.demodocebo.test.data.api.models.RemoteModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by andrea on 28/08/2017.
 */
class SwapiApiDatasource @Inject constructor(api: SwapiApi) {

    private var mSwapiApi: SwapiApi = api

    fun root(): Observable<RemoteModel> {
        return mSwapiApi.root()
                .onErrorCheckRetrofitException()
    }
}