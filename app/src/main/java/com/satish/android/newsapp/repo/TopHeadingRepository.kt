package com.satish.android.newsapp.repo

import com.satish.android.networktest.model.NewsItemModel
import com.satish.android.newsapp.app.NewsApplication
import com.satish.android.newsapp.db.entities.NewsItemEntity
import com.satish.android.newsapp.utility.log
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import java.lang.Exception



class TopHeadingRepository {

    private object Holder {
        val INSTANCE = TopHeadingRepository()
    }

    private val newsListDao by lazy {
        NewsApplication.instance.appDatabase.newsItemDao()
    }

    suspend fun getNewsListFromDb(): ArrayList<NewsItemModel>? {
        var newsList = newsListDao.getAllNewsItems()
        if (newsList.isNullOrEmpty()) return null
        val news = arrayListOf<NewsItemModel>()
        newsList.forEach {
            val newsItem =
                NewsItemModel(
                    null, it.author, it.title, it.description, it.url,
                    it.urlToImage, it.publishedAt, it.content
                )
            news.add(newsItem)
        }
        return news
    }

    suspend fun insertNewsListIntoDb(newsList: ArrayList<NewsItemModel>?) {
        if (newsList == null) return
        var news = ArrayList<NewsItemEntity>()
        newsList.forEach {
            news.add(
                NewsItemEntity(
                    it.author.orEmpty(), it.title.orEmpty(),
                    it.description.orEmpty(), it.url.orEmpty(), it.urlToImage.orEmpty(), it.publishedAt.orEmpty(),
                    it.content.orEmpty()
                )
            )
        }
        try {
            newsListDao.insertAll(news)
        } catch (e: Exception) {
            e.log()
        }
    }

    suspend fun clearNewsDb(){
        newsListDao.clearData()
    }

    companion object {
        val instance by lazy { TopHeadingRepository.Holder.INSTANCE }
    }
}