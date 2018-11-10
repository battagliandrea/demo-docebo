package com.demodocebo.test.data.repositories

import com.demodocebo.test.data.api.DoceboDemoApiDatasource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogRepository @Inject constructor(doceboDemoApi: DoceboDemoApiDatasource){

    private var mDoceboDemoApi : DoceboDemoApiDatasource = doceboDemoApi

//    fun rootList(): Observable<List<String?>> {
//        return mDoceboDemoApi.root()
//                .map{ res -> res.getList() }
//    }

//    fun fetchRootList(): Observable<List<String?>> {
//        return mDoceboDemoApi.root()
//                .map{ res -> res.getList() }
//    }
}
