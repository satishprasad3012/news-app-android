package com.satish.android.newsapp.network

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.text.TextUtils
import com.satish.android.networktest.model.enums.NetworkStatusType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class RetrofitCallback<T : GenericResponse<*>> protected constructor() : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                if (!TextUtils.equals(body.status, NetworkStatusType.SUCCESS)) {
                    onFail(GenericResponse<Any>(data = String,
                        status = NetworkStatusType.ERROR,
                        message = response.message()))
                } else {
                   onSuccess(body)
                }
            } else {
                onFail(GenericResponse<Any>(data = String,
                    status = NetworkStatusType.ERROR,
                    message = response.message()))
            }
        } else {
            onFail(GenericResponse<Any>(data = String,
                status =  NetworkStatusType.ERROR))
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFail(processFail(t, call))
    }

    abstract fun onSuccess(response: T)

    abstract fun onFail(response: GenericResponse<*>)

}
