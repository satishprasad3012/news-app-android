package com.satish.android.newsapp.utility

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast
import com.satish.android.newsapp.network.RetrofitProvider
import com.satish.android.newsapp.network.RetrofitServices

fun Context.toast(@StringRes message: Int) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toast(message: CharSequence?) = message?.let {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Int?.orZero() = this ?: 0


fun retroServices(): RetrofitServices {
    return RetrofitProvider.instance.services
}