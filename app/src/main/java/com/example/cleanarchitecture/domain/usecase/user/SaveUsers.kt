package com.example.cleanarchitecture.domain.usecase.user

import com.example.cleanarchitecture.data.dto.user.User
import com.example.cleanarchitecture.domain.repository.UserRepository

class SaveUsers(private val userRepository: UserRepository) {
    fun invoke(users: List<User>) {
        userRepository.saveUsers(users)
    }
}