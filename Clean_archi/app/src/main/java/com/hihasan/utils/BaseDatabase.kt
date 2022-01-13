package com.hihasan.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hihasan.dao.NotesDao
import com.hihasan.entity.NotesEntity

@Database(entities = [NotesEntity::class], version = 1)
abstract class BaseDatabase : RoomDatabase() {
    companion object{
        private const val DATABASE_NAME = "note.db"

        private var instance: BaseDatabase? = null

        private fun create(context: Context): BaseDatabase =
            Room.databaseBuilder(context, BaseDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


        fun getInstance(context: Context): BaseDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun noteDao() : NotesDao
}