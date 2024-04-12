package com.example.cleanarchitecture.core.domain

import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.core.data.User

interface UserRemoteDataSource {
    fun getAllUsersRemote(): LiveData<List<User>>
}