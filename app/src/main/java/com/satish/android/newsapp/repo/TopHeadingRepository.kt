package com.satish.android.newsapp.repo

import android.arch.lifecycle.MutableLiveData
import com.satish.android.networktest.model.NewsItemModel
import com.satish.android.newsapp.network.GenericResponse
import com.satish.android.newsapp.network.RetrofitCallback
import com.satish.android.newsapp.utility.Constants.DEFUALT_COUNTRY
import com.satish.android.newsapp.utility.Constants.RV_PAGE_SIZE
import com.satish.android.newsapp.utility.retroServices
import retrofit2.Response

class TopHeadingRepository {

    private object Holder {
        val INSTANCE = TopHeadingRepository()
    }
//    fun getTopHeadingList(
//        topHeadingList: MutableLiveData<List<NewsItemModel>>?,
//        errorMesg: MutableLiveData<String>?, page: Int, pageSize: Int = RV_PAGE_SIZE, country: String = DEFUALT_COUNTRY) {
//        retroServices().getTopHeadlines(country, pageSize, page)
//            .enqueue(object : RetrofitCallback<GenericResponse<List<NewsItemModel>>>() {
//
//                override fun onSuccess(response: GenericResponse<List<NewsItemModel>>) {
//                    topHeadingList?.value = response.data
//                }
//
//                override fun onFail(response: GenericResponse<*>) {
//                    errorMesg?.value = response.message
//                }
//            })
//    }

    fun getTopHeadingList(page: Int, pageSize: Int = RV_PAGE_SIZE, country: String = DEFUALT_COUNTRY): Response<GenericResponse<ArrayList<NewsItemModel>>> {
        return retroServices().getTopHeadlines(country, pageSize, page).execute()
    }

    companion object {
        val instance by lazy { TopHeadingRepository.Holder.INSTANCE }
    }
}