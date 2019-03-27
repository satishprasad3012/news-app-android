package com.satish.android.newsapp.ui.activity

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startTopHeadingNewsActivity()
    }

    private fun startTopHeadingNewsActivity() {
        val intent = Intent(this@SplashActivity, TopHeadingActivity::class.java)
        startActivity(intent)
        finish()
    }
}