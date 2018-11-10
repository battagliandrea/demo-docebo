package com.demodocebo.test.data.api

import com.demodocebo.test.data.api.models.RemoteModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by andrea on 23/04/17.
 */

interface DoceboDemoApi {

    @GET("api")
    fun root(): Observable<RemoteModel>
}


