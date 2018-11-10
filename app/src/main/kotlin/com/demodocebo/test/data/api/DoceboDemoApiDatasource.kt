package com.demodocebo.test.data.api

import com.demodocebo.test.data.api.models.RemoteCatalog
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by andrea on 28/08/2017.
 */
class DoceboDemoApiDatasource @Inject constructor(demoApi: DoceboDemoApi) {

    private var mDoceboDemoApi: DoceboDemoApi = demoApi

    fun root(): Observable<RemoteCatalog> {
        return mDoceboDemoApi.root()
                .onErrorCheckRetrofitException()
    }
}