package com.example.cleanarchitecture.framework.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cleanarchitecture.core.data.User
import com.example.cleanarchitecture.core.domain.UserLocalDataSource
import com.example.cleanarchitecture.framework.data.local.db.dao.UserDao
import com.example.cleanarchitecture.framework.data.local.db.entity.UserEntity

class UserLocalDataSourceImpl(private val userDao: UserDao) : UserLocalDataSource {
    private fun UserEntity.toUser(): User =
        User(
            id = id,
            avatar = avatar,
            email = email,
            firstName = firstName,
            lastName = lastName
        )

    override fun getAllUsersLocal(): LiveData<List<User>> {
        return userDao.getAllUsers().map { entities ->
            entities.map { it.toUser() }
        }
    }
}