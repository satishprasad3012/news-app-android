package com.satish.android.newsapp.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.satish.android.networktest.model.NewsItemModel
import java.util.*

class RoomTypeConverter {

    @TypeConverter
    fun fromArray(list: ArrayList<Any>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toStringList(value: String): ArrayList<String>? {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson<ArrayList<String>>(value, listType)
    }

    @TypeConverter
    fun toNewsList(data: String?): List<NewsItemModel> {
        if (data == null) return Collections.emptyList()
        val listType = object : TypeToken<List<NewsItemModel>>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun fromNewsList(data: List<NewsItemModel>): String {
        return Gson().toJson(data)
    }


    @TypeConverter
    fun toNewsItem(data: String): NewsItemModel? {
        val newsType = object : TypeToken<NewsItemModel>() {}.type
        return Gson().fromJson(data, newsType)
    }

    @TypeConverter
    fun fromNewsItem(data: NewsItemModel?): String {
        return if (data != null) Gson().toJson(data) else ""
    }

}