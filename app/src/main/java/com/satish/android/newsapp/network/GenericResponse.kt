package com.satish.android.newsapp.network

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/
import com.google.gson.annotations.SerializedName

class GenericResponse<T>(
    @SerializedName("status") var status: String? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("code") var code: String? = null, // for error code
    @SerializedName("totalResults") val totalResults: Int? = null,
    @SerializedName("articles") var data: T? = null
)