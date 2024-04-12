package com.example.cleanarchitecture.core.domain

import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.core.data.User

class UserRepository(private val userDataSource: UserDataSource) {

    fun getAllUsers(): LiveData<List<User>> = userDataSource.getAllUsers()

}