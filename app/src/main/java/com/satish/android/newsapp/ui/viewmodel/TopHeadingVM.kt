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
import com.satish.android.newsapp.R
import com.satish.android.newsapp.app.NewsApplication
import com.satish.android.newsapp.network.GenericResponse
import com.satish.android.newsapp.network.RetrofitCallback
import com.satish.android.newsapp.repo.TopHeadingRepository
import com.satish.android.newsapp.utility.Constants
import com.satish.android.newsapp.utility.isNetworkAvailable
import com.satish.android.newsapp.utility.retroServices
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class TopHeadingVM : ViewModel() {

    val newsData = MutableLiveData<ArrayList<NewsItemModel>>()
    val errorMsg = MutableLiveData<String>()
    val totalResults = MutableLiveData<Int>()

    fun topHeadingCallAPI(
        page: Int, country: String = Constants.DEFUALT_COUNTRY,
        pageSize: Int = Constants.RV_PAGE_SIZE
    ) {
        if (!isNetworkAvailable) {
            if (page == 0)
                getNewsDataFromDb()
        } else {
            retroServices().getTopHeadlines(country, pageSize, page)
                .enqueue(object : RetrofitCallback<GenericResponse<ArrayList<NewsItemModel>>>() {

                    override fun onSuccess(response: GenericResponse<ArrayList<NewsItemModel>>) {
                        newsData.value = response.data
                        totalResults.value = response.totalResults
                        if (page == 0) clearNewsDataAndInsert(response.data)
                        else
                            insertNewsIntoDb(response.data)
                    }

                    override fun onFail(response: GenericResponse<*>) {
                        errorMsg.value = response.message
                    }
                })
        }
    }

    private fun insertNewsIntoDb(newsList: ArrayList<NewsItemModel>?) {
        launch {
            TopHeadingRepository.instance.insertNewsListIntoDb(newsList)
        }
    }

    private fun getNewsDataFromDb() {
        launch {
            val newsList = TopHeadingRepository.instance.getNewsListFromDb()
            if (newsList != null && newsList.isNotEmpty())
                newsData.postValue(newsList)
            errorMsg.postValue(NewsApplication.instance.getString(R.string.no_internet))
        }
    }

    private fun clearNewsDataAndInsert(newsList: ArrayList<NewsItemModel>?) {
        launch {
            TopHeadingRepository.instance.clearNewsDb()
            TopHeadingRepository.instance.insertNewsListIntoDb(newsList)
        }
    }
}