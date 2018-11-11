package com.demodocebo.test.data.repositories

import com.demodocebo.test.data.api.DoceboDemoApiDatasource
import com.demodocebo.test.data.api.models.Catalog
import com.demodocebo.test.data.api.models.Item
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CatalogRepository @Inject constructor(doceboDemoApi: DoceboDemoApiDatasource){

    private var mDoceboDemoApi : DoceboDemoApiDatasource = doceboDemoApi

    private var type: BehaviorSubject<String> = BehaviorSubject.create()
    private var name: BehaviorSubject<String> = BehaviorSubject.create()
    private var page: BehaviorSubject<Int> = BehaviorSubject.createDefault(0)


    fun saveSearchParames(name: String, type: String): Observable<Boolean> {
        return Observable.fromCallable {
            this.type.onNext(type)
            this.name.onNext(name)
            return@fromCallable true
        }
    }

    fun fetchCatalogItems(): Observable<Catalog> {
        return mDoceboDemoApi.catalog(name.value ?: "", type.value ?: "all", page.value ?: 0)
    }
}
