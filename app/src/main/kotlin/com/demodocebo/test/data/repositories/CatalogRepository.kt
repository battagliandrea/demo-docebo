package com.demodocebo.test.data.repositories

import com.demodocebo.test.data.api.DoceboDemoApiDatasource
import com.demodocebo.test.data.api.models.Item
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CatalogRepository @Inject constructor(doceboDemoApi: DoceboDemoApiDatasource){

    private var mDoceboDemoApi : DoceboDemoApiDatasource = doceboDemoApi

    private var type: String = ""
    private var name: String = ""
    private var page: Int = 0

    fun fetchCatalogItems(): Observable<List<Item>> {
        return mDoceboDemoApi.catalog("", "all", 0)
                .map{ res -> res.data.items }
    }
}
