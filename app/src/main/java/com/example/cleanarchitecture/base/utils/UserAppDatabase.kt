package com.example.cleanarchitecture.base.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.data.local.dao.UserDao
import com.example.cleanarchitecture.data.local.model.get_users.UserEntity


@Database(
    version = 1, entities = [UserEntity::class]
)
abstract class UserAppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserAppDatabase? = null
        private val mLock = Any()

        fun getInstance(context: Context): UserAppDatabase = INSTANCE ?: synchronized(mLock) {
            Room.databaseBuilder(
                context.applicationContext, UserAppDatabase::class.java, "test"
            ).allowMainThreadQueries().build().apply { INSTANCE = this }
        }
    }
}