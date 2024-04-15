package com.example.cleanarchitecture.domain.repository

import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.data.dto.user.User

interface UserRepository {
    fun getAllUsers(): LiveData<List<User>>
    fun saveUsers(users: List<User>)
}