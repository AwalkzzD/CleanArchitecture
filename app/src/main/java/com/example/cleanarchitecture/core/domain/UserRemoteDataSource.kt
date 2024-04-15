package com.example.cleanarchitecture.core.domain

import com.example.cleanarchitecture.core.data.User

interface UserRemoteDataSource {
    fun getAllUsersRemote(): List<User>
}