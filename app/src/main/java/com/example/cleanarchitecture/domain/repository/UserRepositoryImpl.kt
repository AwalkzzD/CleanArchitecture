package com.example.cleanarchitecture.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.base.extensions.toLiveData
import com.example.cleanarchitecture.data.dto.user.User

private const val TAG = "UserRepositoryImpl"

class UserRepositoryImpl(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {
    override fun getAllUsers(): LiveData<List<User>> {
        return try {
            if (localDataSource.getAllUsersLocal().isEmpty()) {
                remoteDataSource.getAllUsersRemote()
            } else {
                localDataSource.getAllUsersLocal().toLiveData()
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