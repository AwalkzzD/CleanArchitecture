package com.example.cleanarchitecture.framework.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cleanarchitecture.core.data.User
import com.example.cleanarchitecture.core.domain.UserDataSource
import com.example.cleanarchitecture.framework.data.local.db.dao.UserDao
import com.example.cleanarchitecture.framework.data.local.db.entity.UserEntity

class UserLocalDataSourceImpl(private val userDao: UserDao) : UserDataSource {
    override fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers().map { entities ->
            entities.map { it.toUser() }
        }
    }

    private fun UserEntity.toUser(): User =
        User(
            id = id,
            avatar = avatar,
            email = email,
            firstName = firstName,
            lastName = lastName
        )
}