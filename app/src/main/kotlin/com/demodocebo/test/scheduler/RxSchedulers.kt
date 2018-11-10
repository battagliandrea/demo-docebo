package com.demodocebo.test.scheduler


import io.reactivex.Scheduler

/**
 * Created by ygharsallah on 30/03/2017.
 */

interface RxSchedulers {


    fun runOnBackground(): Scheduler

    fun io(): Scheduler

    fun compute(): Scheduler

    fun androidThread(): Scheduler

    fun internet(): Scheduler


}
