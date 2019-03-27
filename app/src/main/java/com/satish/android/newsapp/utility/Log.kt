package com.satish.android.newsapp.utility

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.util.Log
import com.satish.android.newsapp.BuildConfig

val isLog = BuildConfig.DEBUG

private fun Any.tag(): String {
    return try {
        if (this::class.java.simpleName.isNullOrEmpty()) "Empty" else this::class.java.simpleName
    } catch (e: Exception) {
        "EmptyTag"
    }
}

fun java.lang.Exception.log() {
    if (isLog) {
        Log.e(tag(), "", this)
    }
}