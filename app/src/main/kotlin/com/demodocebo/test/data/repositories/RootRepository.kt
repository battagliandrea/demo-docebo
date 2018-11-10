package com.demodocebo.test.data.repositories

import com.demodocebo.test.data.api.SwapiApiDatasource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RootRepository @Inject constructor(swapiApi: SwapiApiDatasource){

    private var mSwapiApi : SwapiApiDatasource = swapiApi

//    fun rootList(): Observable<List<String?>> {
//        return mSwapiApi.root()
//                .map{ res -> res.getList() }
//    }

//    fun fetchRootList(): Observable<List<String?>> {
//        return mSwapiApi.root()
//                .map{ res -> res.getList() }
//    }
}
