package com.satish.android.newsapp.ui.viewmodel

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.content.Context
import android.view.View
import com.satish.android.networktest.model.NewsItemModel
import com.satish.android.newsapp.ui.activity.NewsDetailActivity

class NewsItemBinder(private val context: Context, private val newItem: NewsItemModel?) {

    val title = newItem?.title.orEmpty()
    val description = newItem?.description.orEmpty()
    val urlToImage = newItem?.urlToImage.orEmpty()
    val publishedAt = newItem?.publishedAt.orEmpty()
    val content = newItem?.content.orEmpty()

    fun onItemClick() = View.OnClickListener {
        newItem?.url?.let {
            NewsDetailActivity.startActivity(context, newItem.url, newItem.title.orEmpty())
        }
    }
}
