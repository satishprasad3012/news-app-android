package com.satish.android.newsapp.ui.activity

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.webkit.WebViewClient
import com.satish.android.newsapp.R
import com.satish.android.newsapp.analytics.NewsAnalytics
import com.satish.android.newsapp.databinding.NewsDetailActivityBinding
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.os.Build
import android.support.annotation.RequiresApi
import android.graphics.Bitmap
import android.webkit.WebResourceError
import com.satish.android.newsapp.utility.fadeOut


class NewsDetailActivity : BaseActivity() {

    lateinit var binding: NewsDetailActivityBinding
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.news_detail_activity)
        getIntentValues()
        initUi()
    }

    private fun getIntentValues() {
        url = intent?.extras?.getString(NEWS_DETAIL_URL, null)
      //  val title = intent?.extras?.getString(NEWS_TITLE, "") // may be used in future
       // setToolbarHeading(title.orEmpty())
    }

    private fun initUi() {
        val webSettings = binding.newsDetailWv.settings
        webSettings.javaScriptEnabled = true
        setWebViewClient()
        binding.newsDetailWv.loadUrl(url)
    }

    private fun setWebViewClient(){
        binding.newsDetailWv.webViewClient = (object : WebViewClient() {

            @SuppressWarnings("deprecation")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                fadeOut(binding.loadingLay.loadingTv)
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                binding.loadingLay.loadingTv.text = resources.getString(R.string.error)
                super.onReceivedError(view, request, error)
            }
        })
    }

    override fun onBackPressed() {
        if (binding.newsDetailWv.canGoBack()) {
            binding.newsDetailWv.goBack()
        } else {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    companion object {
        private const val NEWS_DETAIL_URL = "NEWS_DETAIL_URL"
        private const val NEWS_TITLE = "NEWS_TITLE" // used may be in future

        fun startActivity(context: Context, url: String? = null, title: String? = null) {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(NEWS_DETAIL_URL, url)
            intent.putExtra(NEWS_TITLE, title)
            context.startActivity(intent)
        }
    }

    override val screenName = NewsAnalytics.NEWS_DETAIL_SCREEN
}