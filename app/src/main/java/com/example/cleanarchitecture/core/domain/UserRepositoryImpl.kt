package com.example.cleanarchitecture.core.domain

import android.util.Log
import com.example.cleanarchitecture.core.data.User

private const val TAG = "UserRepositoryImpl"

class UserRepositoryImpl(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {
    override fun getAllUsers(): List<User> {
        return try {
            localDataSource.getAllUsersLocal().ifEmpty {
                Log.d(TAG, "getAllUsers: ${remoteDataSource.getAllUsersRemote()}")
                remoteDataSource.getAllUsersRemote()
            }
        } catch (e: Exception) {
            Log.d(TAG, "getAllUsers: $e")
            remoteDataSource.getAllUsersRemote()
        }
    }

    override fun saveUsers(users: List<User>) {
        try {
            localDataSource.saveUsersLocal(users)
        } catch (e: Exception) {
            Log.d("TAG", "error storing in db $e")
        }
    }
}