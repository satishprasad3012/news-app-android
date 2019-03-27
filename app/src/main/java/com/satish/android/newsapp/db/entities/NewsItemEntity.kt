package com.satish.android.newsapp.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = NewsItemEntity.TABLE_NAME)
data class NewsItemEntity(

    @ColumnInfo(name = AUTHOR) var author: String,

    @ColumnInfo(name = TITLE) var title: String,

    @ColumnInfo(name = DESCRIPTION) var description: String,

    @PrimaryKey
    @ColumnInfo(name = URL) var url: String,

    @ColumnInfo(name = URL_TO_IMAGE) var urlToImage: String,

    @ColumnInfo(name = PUBLISHED_AT) var publishedAt: String,

    @ColumnInfo(name = CONTENT) var content: String
) {
    companion object {
        const val TABLE_NAME = "top_headline"
        const val AUTHOR = "author"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val URL = "url"
        const val URL_TO_IMAGE = "urlToImage"
        const val PUBLISHED_AT = "publishedAt"
        const val CONTENT = "content"
    }
}