package com.demodocebo.test.route

import android.app.Activity
import com.demodocebo.test.ui.view.home.HomeActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class RouteManager @Inject constructor() {


    open fun launchShowList(activity: Activity) {
        activity.startActivity(HomeActivity.getCallingIntent(activity))
    }

}