package com.satish.android.newsapp.utility

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.content.Context
import android.net.ConnectivityManager
import android.provider.Settings
import com.satish.android.newsapp.app.NewsApplication
import java.util.*

val deviceId: String
    get() = Settings.System.getString(
        NewsApplication.instance.applicationContext.contentResolver,
        Settings.Secure.ANDROID_ID
    )

val defaultLocale: String
    get() {
        try {
            return Locale.getDefault().toString()
        } catch (ignored: Exception) {

        }

        return ""
    }

val isNetworkAvailable: Boolean
    get() = isNetworkAvailable(NewsApplication.instance)

fun isNetworkAvailable(context: Context): Boolean {
    val mCM = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = mCM.activeNetworkInfo
    return netInfo != null && netInfo.isConnectedOrConnecting
}
