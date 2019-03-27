package com.satish.android.newsapp.ui.adapter

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.satish.android.networktest.model.NewsItemModel
import com.satish.android.newsapp.R
import com.satish.android.newsapp.databinding.TopHeadingRowItemBinding
import com.satish.android.newsapp.ui.viewholder.TopHeadingViewHolder
import com.satish.android.newsapp.utility.orZero
import java.util.ArrayList

class TopHeadingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var newsList: ArrayList<NewsItemModel> = ArrayList()
    private lateinit var inflater: LayoutInflater

    fun setData(newsList: ArrayList<NewsItemModel>?) {
        if (newsList == null)
            this.newsList = ArrayList()
        else
            this.newsList = newsList
        notifyDataSetChanged()
    }

    fun addData(newsList: List<NewsItemModel>) {
        val startingPos = this.newsList.size
        this.newsList.addAll(newsList)
        notifyItemRangeInserted(startingPos, newsList.size)
    }

    fun getTotalItemCount(): Int{
        return this.newsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return TopHeadingViewHolder(
            DataBindingUtil.inflate<TopHeadingRowItemBinding>(inflater, R.layout.top_heading_row_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return newsList.size.orZero()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        if (holder is TopHeadingViewHolder && newsList.size.orZero() > pos) holder.bindTo(newsList.get(pos))
    }
}