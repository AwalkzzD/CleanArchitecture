package com.example.cleanarchitecture.domain.usecase.user

import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.data.dto.user.User
import com.example.cleanarchitecture.domain.repository.UserRepository

class GetAllUsers(private val userRepository: UserRepository) {

    fun invoke(): LiveData<List<User>> = userRepository.getAllUsers()

}