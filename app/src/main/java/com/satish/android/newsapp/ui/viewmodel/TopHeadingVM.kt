package com.satish.android.newsapp.ui.viewmodel

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.satish.android.networktest.model.NewsItemModel
import com.satish.android.newsapp.network.GenericResponse
import com.satish.android.newsapp.network.RetrofitCallback
import com.satish.android.newsapp.utility.Constants
import com.satish.android.newsapp.utility.retroServices

class TopHeadingVM : ViewModel() {

    val newsData = MutableLiveData<ArrayList<NewsItemModel>>()
    val errorMsg = MutableLiveData<String>()
    val totalResults = MutableLiveData<Int>()

    fun topHeadingCallAPI(
        page: Int, country: String = Constants.DEFUALT_COUNTRY,
        pageSize: Int = Constants.RV_PAGE_SIZE
    ) {
        retroServices().getTopHeadlines(country, pageSize, page)
            .enqueue(object : RetrofitCallback<GenericResponse<ArrayList<NewsItemModel>>>() {

                override fun onSuccess(response: GenericResponse<ArrayList<NewsItemModel>>) {
                    newsData.value = response.data
                    totalResults.value = response.totalResults
                }

                override fun onFail(response: GenericResponse<*>) {
                    errorMsg.value = response.message
                }
            })
    }
}