package com.example.cleanarchitecture.data.repository.user

import com.example.cleanarchitecture.base.extensions.toUser
import com.example.cleanarchitecture.base.extensions.toUserEntity
import com.example.cleanarchitecture.data.dto.user.User
import com.example.cleanarchitecture.data.local.dao.UserDao
import com.example.cleanarchitecture.domain.repository.UserLocalDataSource

class UserLocalDataSourceImpl(private val userDao: UserDao) : UserLocalDataSource {

    override fun getAllUsersLocal(): List<User> {
        return userDao.getAllUsers().map { it.toUser() }
    }

    override fun saveUsersLocal(users: List<User>) {
        for (user in users) {
            userDao.saveUsersLocal(user.toUserEntity())
        }
    }
}