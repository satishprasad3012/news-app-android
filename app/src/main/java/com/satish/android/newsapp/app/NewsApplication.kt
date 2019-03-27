package com.satish.android.newsapp.app

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.support.multidex.MultiDexApplication

class NewsApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: NewsApplication
    }
}


