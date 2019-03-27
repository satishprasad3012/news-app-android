package com.satish.android.newsapp.network

import com.satish.android.networktest.model.NewsItemModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author satish
 * 27/03/2019
 *
 * here need to add all API call methods
 *
 * ***/


interface RetrofitServices {

    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int): Call<GenericResponse<ArrayList<NewsItemModel>>>

}