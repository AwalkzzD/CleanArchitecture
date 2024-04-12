package com.example.cleanarchitecture.framework.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.cleanarchitecture.framework.data.local.db.dao.UserDao
import com.example.cleanarchitecture.framework.data.local.db.entity.UserEntity


@Database(
    version = 1, entities = [UserEntity::class]
)
abstract class UserAppDatabase : RoomDatabase() {
    private var databaseClient: UserAppDatabase? = null

    private fun getDatabaseClient(context: Context?): UserAppDatabase? {
        if (databaseClient == null) {
            databaseClient = databaseBuilder(
                context!!, UserAppDatabase::class.java, "test"
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }
        return databaseClient
    }

    abstract fun userDao(): UserDao

    fun getUserDao(context: Context?): UserDao? {
        return getDatabaseClient(context)?.userDao()
    }
}