package com.demodocebo.test.route

import android.app.Activity
import com.demodocebo.test.ui.view.catalog.CatalogActivity
import com.demodocebo.test.ui.view.search.SearchActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class RouteManager @Inject constructor() {


    open fun launchSearch(activity: Activity) {
        activity.startActivity(SearchActivity.getCallingIntent(activity))
    }

    open fun launchCatalog(activity: Activity) {
        activity.startActivity(CatalogActivity.getCallingIntent(activity))
    }

}