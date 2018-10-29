package com.mcs.kalherath.recipepuppy

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

        @GET("api/")
        fun getData(@Query("i") ingredients: String, @Query("q") dish: String, @Query("p") page: String): Observable<Results>
}
