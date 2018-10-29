package com.mcs.kalherath.recipepuppy

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ItemModel{

    private val BASE_URL = "http://www.recipepuppy.com/"

    var mList : MutableLiveData<MutableList<Item>> = MutableLiveData()

    init {
        mList.value = ArrayList()
    }

    fun loadJSON(i : String, d : String, p : String){
        Log.e("TAGTAGTAG", "start")

        val retrofitInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitInterface::class.java)

        val JSONresponse = retrofitInterface.getData(i, d, p)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())

            JSONresponse.subscribeBy(
                            onNext = { handleResponse(it) },
                            onError = { handleError(it) },
                            onComplete = {}
                    )

    }

    private fun handleResponse(iResults: Results) {
        mList.value = iResults.results
        Log.e("TAGTAGTAG", "GOT ITEMS")
    }

    private fun handleError(error: Throwable) {
        Log.e("TAGTAGTAG", "ERROR")
    }

    fun getRecipes(i: String, d: String){
        mList.value = ArrayList()
        loadJSON(i, d, "3")
    }
}