package com.satish.android.newsapp.ui.binding

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.satish.android.newsapp.app.GlideApp
import com.satish.android.newsapp.utility.log

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun setImageUrl(view: ImageView, imageUrl: String?, placeHolder: Drawable?) {
    try {
        GlideApp.with(view.context)
            .load(imageUrl)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(placeHolder)
            .skipMemoryCache(true)
            .timeout(15000)
            .priority(Priority.IMMEDIATE)
            .into(view)
    } catch (e: Exception) {
        e.log()
    }
}