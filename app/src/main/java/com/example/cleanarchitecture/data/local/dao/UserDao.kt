package com.example.cleanarchitecture.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchitecture.data.local.model.get_users.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * from users")
    fun getAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsersLocal(vararg user: UserEntity)
}