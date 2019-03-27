package com.satish.android.newsapp.network

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

class ErrorResponse(
    var statusCode: Int = 0,
    var message: String = "",
    val desc: String = "")
