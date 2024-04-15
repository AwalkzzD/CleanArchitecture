package com.example.cleanarchitecture.domain.repository

import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.data.dto.user.User

interface UserRemoteDataSource {
    fun getAllUsersRemote(): LiveData<List<User>>
}