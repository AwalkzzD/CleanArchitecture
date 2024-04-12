package com.example.cleanarchitecture.framework.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.cleanarchitecture.framework.data.local.db.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * from users")
    fun getAllUsers(): LiveData<List<UserEntity>>
}