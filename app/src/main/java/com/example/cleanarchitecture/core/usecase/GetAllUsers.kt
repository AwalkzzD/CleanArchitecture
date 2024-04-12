package com.example.cleanarchitecture.core.usecase

import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.core.data.User
import com.example.cleanarchitecture.core.domain.UserRepository

class GetAllUsers(private val userRepository: UserRepository) {

    fun invoke(): LiveData<List<User>> = userRepository.getAllUsers()

}