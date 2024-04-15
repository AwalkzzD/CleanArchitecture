package com.example.cleanarchitecture.data.repository.user

import com.example.cleanarchitecture.data.dto.user.User
import com.example.cleanarchitecture.domain.repository.UserLocalDataSource
import com.example.cleanarchitecture.data.local.dao.UserDao
import com.example.cleanarchitecture.data.local.model.get_users.UserEntity

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