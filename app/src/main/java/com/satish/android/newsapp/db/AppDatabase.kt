package com.satish.android.newsapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.satish.android.newsapp.R
import com.satish.android.newsapp.db.daos.NewsItemDao
import com.satish.android.newsapp.db.entities.NewsItemEntity


@Database(entities = [NewsItemEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(RoomTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsItemDao(): NewsItemDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildRoomDatabase(context).also { instance = it }
            }
        }

        private fun buildRoomDatabase(context: Context): AppDatabase {
            val buildRoom =
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, context.getString(R.string.database))
                    .fallbackToDestructiveMigration()

            return buildRoom.build()
        }
    }
}