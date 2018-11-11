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

    private var count: BehaviorSubject<Int> = BehaviorSubject.createDefault(0)
    private var page: BehaviorSubject<Int> = BehaviorSubject.createDefault(0)
    private var hasMoreData: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(true)

    internal var mItemsCached = mutableListOf<Item>()


    fun saveSearchParames(name: String, type: String): Observable<Boolean> {
        return Observable.fromCallable { true }
                .doOnNext { this.count.onNext(0) }
                .doOnNext { this.page.onNext(0) }
                .doOnNext { this.hasMoreData.onNext(true) }
                .doOnNext { this.mItemsCached.clear() }

                .doOnNext { this.type.onNext(type)  }
                .doOnNext { this.name.onNext(name)  }
    }

    fun fetchCatalogItems(): Observable<Pair<Int, List<Item>>> {
        return Observable
                .defer{
                    if(hasMoreData.value == true){

                        var p = this.page.value?.plus(1)

                        mDoceboDemoApi.catalog(name.value ?: "", type.value ?: "all", p ?: 0)
                                .doOnNext { this.page.onNext(it.data.current_page) }
                                .doOnNext { this.count.onNext(it.data.total_count) }
                                .doOnNext { this.hasMoreData.onNext(it.data.has_more_data) }
                                .doOnNext { this.mItemsCached.addAll(it.data.items)  }
                                .map { Pair(it.data.total_count, it.data.items) }
                    } else {
                        Observable.fromCallable { Pair(count.value ?: 0, ArrayList<Item>()) }
                    }
                }
    }
}
