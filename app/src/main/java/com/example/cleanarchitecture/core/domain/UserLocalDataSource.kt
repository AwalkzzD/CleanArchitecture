package com.example.cleanarchitecture.core.domain

import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.core.data.User

interface UserLocalDataSource {
    fun getAllUsersLocal(): LiveData<List<User>>
}