package com.example.cleanarchitecture.core.domain

import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.core.data.User

class UserRepositoryImpl(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {
    override fun getAllUsers(): LiveData<List<User>> {
        return try {
            localDataSource.getAllUsersLocal()
        } catch (_: Exception) {
            remoteDataSource.getAllUsersRemote()
        }
    }
}