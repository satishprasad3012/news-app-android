package com.satish.android.newsapp.ui.activity

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.satish.android.newsapp.R
import com.satish.android.newsapp.analytics.NewsAnalytics
import com.satish.android.newsapp.databinding.TopHeadingActivityBinding
import com.satish.android.newsapp.ui.adapter.TopHeadingAdapter
import com.satish.android.newsapp.ui.listener.PaginationScrollListener
import com.satish.android.newsapp.ui.viewmodel.TopHeadingVM
import com.satish.android.newsapp.utility.*

class TopHeadingActivity : BaseActivity() {

    private lateinit var binding: TopHeadingActivityBinding
    private val topHeadingVM by lazy { ViewModelProviders.of(this).get(TopHeadingVM::class.java) }
    private var adapter: TopHeadingAdapter? = null
    private var paginationScrollListener: PaginationScrollListener? = null
    private var page: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.top_heading_activity)
        setToolbarHeading(getString(R.string.top_heading))
        initUi()
    }

    private fun initUi() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        paginationScrollListener = object :
            PaginationScrollListener(layoutManager) {
            override fun onLoadMore(p: Int, totalItemsCount: Int, view: RecyclerView) {
                page = p
                paginationScrollListener?.setLoading(true)
                topHeadingVM.topHeadingCallAPI(p)
            }
        }
        adapter = TopHeadingAdapter()
        binding.newsfeedRv.adapter = adapter
        binding.newsfeedRv.layoutManager = layoutManager
        if (paginationScrollListener != null)
            binding.newsfeedRv.addOnScrollListener(paginationScrollListener!!)

        binding.loadingLay.loadingTv.visibility = View.VISIBLE
        topHeadingVM.newsData.observeNullable(this, {
            if (it == null || it.isEmpty())
                handleNoResult()
            else {
                binding.errorLay.errorTv.visibility = View.GONE
                binding.loadingLay.loadingTv.visibility = View.GONE
                if (page == 0)
                    adapter?.setData(it)
                else
                    adapter?.addData(it)
            }
        })
        topHeadingVM.topHeadingCallAPI(0)
        binding.swipeToRefresh.setOnRefreshListener {
            fadeIn(binding.loadingLay.loadingTv)
            topHeadingVM.topHeadingCallAPI(0)
            binding.swipeToRefresh.isRefreshing = false
        }
        checkMoreItems()
        handleError()
    }

    private fun checkMoreItems() {
        topHeadingVM.totalResults.observeNonNull(this, {
            if (adapter != null && adapter!!.getTotalItemCount() < it)
                paginationScrollListener?.setHasMore(true)
        })
    }

    private fun handleNoResult() {
        if (page != 0) {
            toast(getString(R.string.no_more_result))
            return
        }
        binding.errorLay.errorTv.visibility = View.VISIBLE
        binding.loadingLay.loadingTv.visibility = View.GONE
        binding.errorLay.errorTv.text = getString(R.string.no_result)
    }

    private fun handleError() {
        topHeadingVM.errorMsg.observeNonNull(this, {
            binding.loadingLay.loadingTv.visibility = View.GONE
            fadeIn(binding.errorLay.errorTv)
            binding.errorLay.errorTv.text = topHeadingVM.errorMsg.value
            toast(topHeadingVM.errorMsg.value)
        })
    }

    override val screenName = NewsAnalytics.NEWS_TOP_HEADING_SCREEN
}