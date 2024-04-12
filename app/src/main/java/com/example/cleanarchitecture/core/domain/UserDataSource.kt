package com.example.cleanarchitecture.core.domain

import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.core.data.User

interface UserDataSource {

    fun getAllUsers(): LiveData<List<User>>

}