package com.demodocebo.test.data.api

import com.demodocebo.test.data.api.models.Catalog
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by andrea on 23/04/17.
 */

interface DoceboDemoApi {

    @GET("catalog")
    fun catalog(
            @Query("search") search: String,
            @Query("type[]") type: String,
            @Query("page") page: Int
    ): Observable<Catalog>
}


