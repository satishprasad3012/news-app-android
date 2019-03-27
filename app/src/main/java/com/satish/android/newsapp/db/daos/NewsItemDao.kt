package com.satish.android.newsapp.db.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.satish.android.newsapp.db.BaseDao
import com.satish.android.newsapp.db.entities.NewsItemEntity

@Dao
interface NewsItemDao : BaseDao<NewsItemEntity> {

    @Query("SELECT * FROM " + NewsItemEntity.TABLE_NAME)
    fun getAllNewsItems(): List<NewsItemEntity>?

    @Query("DELETE FROM " + NewsItemEntity.TABLE_NAME)
    fun clearData()
}