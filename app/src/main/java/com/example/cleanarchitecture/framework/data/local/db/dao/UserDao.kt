package com.example.cleanarchitecture.framework.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchitecture.framework.data.local.db.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * from users")
    fun getAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsersLocal(vararg user: UserEntity)
}