package com.demodocebo.test.data.repositories

import com.demodocebo.test.data.api.DoceboDemoApiDatasource
import com.demodocebo.test.data.api.models.Item
import com.demodocebo.test.ui.view.catalog.SortType
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*
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
    private var sortType: BehaviorSubject<SortType> = BehaviorSubject.createDefault(SortType.NONE)

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

    fun fetchCatalogItems(sortType: SortType): Observable<Pair<Int, List<Item>>> {
        return Observable
                .defer{
                    if(this.sortType.value != sortType){
                        Observable.fromCallable { true }
                                .doOnNext { this.count.onNext(0) }
                                .doOnNext { this.page.onNext(0) }
                                .doOnNext { this.hasMoreData.onNext(true) }
                                .doOnNext { this.mItemsCached.clear() }

                                .doOnNext { this.sortType.onNext(sortType) }

                                .switchMap{callCatalogApi(this.page.value ?: 0)}

                    } else {
                        if(hasMoreData.value == true){
                            var p = this.page.value?.plus(1)
                            callCatalogApi(p ?: 0)
                        } else {
                            Observable.fromCallable { Pair(count.value ?: 0, ArrayList<Item>()) }
                        }
                    }
                }
    }

    private fun callCatalogApi(p: Int) : Observable<Pair<Int, List<Item>>>{
        return mDoceboDemoApi.catalog(name.value ?: "", type.value ?: "all", p ?: 0)
                .doOnNext { this.page.onNext(it.data.current_page) }
                .doOnNext { this.count.onNext(it.data.total_count) }
                .doOnNext { this.hasMoreData.onNext(it.data.has_more_data) }

                .map { catalog ->
                    when(this.sortType.value){
                        SortType.ASC ->{
                            var list = catalog.data.items.toMutableList()
                            var sortedList = list.sortedWith(compareBy { it.item_name })
                            Pair(catalog.data.total_count, sortedList)
                        }
                        SortType.DESC ->{
                            var list = catalog.data.items.toMutableList()
                            list.sortWith(Comparator { o1, o2 -> o1.item_name.compareTo(o2.item_name)})
                            var sortedList = list.sortedWith(compareByDescending { it.item_name })
                            Pair(catalog.data.total_count, sortedList)
                        }
                        else -> Pair(catalog.data.total_count, catalog.data.items)
                    }
                }
                .doOnNext { this.mItemsCached.addAll(it.second)  }
    }
}
