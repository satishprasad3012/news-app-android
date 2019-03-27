package com.satish.android.newsapp.ui.viewholder

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.support.v7.widget.RecyclerView
import com.satish.android.networktest.model.NewsItemModel
import com.satish.android.newsapp.databinding.TopHeadingRowItemBinding
import com.satish.android.newsapp.ui.viewmodel.NewsItemBinder

class TopHeadingViewHolder(val binding: TopHeadingRowItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(news: NewsItemModel) {
        binding.data = NewsItemBinder(binding.root.context,news)
    }
}