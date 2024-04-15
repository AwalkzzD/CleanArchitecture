package com.example.cleanarchitecture.framework.data.local

import com.example.cleanarchitecture.core.data.User
import com.example.cleanarchitecture.core.domain.UserLocalDataSource
import com.example.cleanarchitecture.framework.data.local.db.dao.UserDao
import com.example.cleanarchitecture.framework.data.local.db.entity.UserEntity

private const val TAG = "UserLocalDataSourceImpl"

class UserLocalDataSourceImpl(private val userDao: UserDao) : UserLocalDataSource {
    private fun UserEntity.toUser(): User = User(
        id = id, avatar = avatar, email = email, firstName = firstName, lastName = lastName
    )

    private fun User.toUserEntity(): UserEntity = UserEntity(
        id = id, avatar = avatar, email = email, firstName = firstName, lastName = lastName
    )

    override fun getAllUsersLocal(): List<User> {
        return userDao.getAllUsers().map { it.toUser() }
    }

    override fun saveUsersLocal(users: List<User>) {
        for (user in users) {
            userDao.saveUsersLocal(user.toUserEntity())
        }
    }
}