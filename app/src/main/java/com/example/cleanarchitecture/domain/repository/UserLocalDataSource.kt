package com.example.cleanarchitecture.domain.repository

import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.data.dto.user.User

interface UserLocalDataSource {
    fun getAllUsersLocal(): List<User>
    fun saveUsersLocal(users: List<User>)
}