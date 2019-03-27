package com.satish.android.newsapp.app

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.support.multidex.MultiDexApplication
import com.satish.android.newsapp.db.AppDatabase

class NewsApplication : MultiDexApplication() {

    val appDatabase by lazy { AppDatabase.get(this) }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: NewsApplication
    }
}


