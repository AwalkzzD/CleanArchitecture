package com.example.cleanarchitecture.core.domain

import com.example.cleanarchitecture.core.data.User

interface UserRepository {
    fun getAllUsers(): List<User>
    fun saveUsers(users: List<User>)
}