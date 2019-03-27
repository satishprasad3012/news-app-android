package com.satish.android.newsapp.network

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import com.satish.android.networktest.model.enums.NetworkStatusType
import com.satish.android.newsapp.R
import com.satish.android.newsapp.app.NewsApplication
import com.satish.android.newsapp.utility.log
import com.satish.android.newsapp.utility.toast
import retrofit2.Call
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


fun <T> processFail(e: Throwable, call: Call<T>): GenericResponse<Any> {
    val response = GenericResponse<Any>(data = String)
    if (e is UnknownHostException || e is ConnectException) {
        NewsApplication.instance.applicationContext.toast(R.string.no_internet)
        response.message =
            NewsApplication.instance.applicationContext.getString(R.string.no_internet)
        response.status = NetworkStatusType.ERROR
    } else if (e is SocketTimeoutException) {
        response.message =
            NewsApplication.instance.applicationContext.getString(R.string.internet_too_slow)
        response.status = NetworkStatusType.ERROR
    } else if (call.isCanceled) {
        response.message = "Cancelled"
        response.status =  NetworkStatusType.ERROR
    } else {
        try {
            response.message = e.localizedMessage
        } catch (e: Exception) {
            e.log()
        }
    }
    return response
}