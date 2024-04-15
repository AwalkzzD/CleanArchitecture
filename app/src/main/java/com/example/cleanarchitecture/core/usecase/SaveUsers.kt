package com.example.cleanarchitecture.core.usecase

import com.example.cleanarchitecture.core.data.User
import com.example.cleanarchitecture.core.domain.UserRepository

class SaveUsers(private val userRepository: UserRepository) {

    fun invoke(users: List<User>) {
        userRepository.saveUsers(users)
    }
}