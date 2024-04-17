package com.example.cleanarchitecture.domain.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.cleanarchitecture.base.extensions.isNetworkAvailable
import com.example.cleanarchitecture.base.extensions.toLiveData
import com.example.cleanarchitecture.data.dto.user.User

private const val TAG = "UserRepositoryImpl"

class UserRepositoryImpl(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource,
    private val context: Context
) : UserRepository {
    override fun getAllUsers(currentPage: Int): LiveData<List<User>> =
        if (isNetworkAvailable(context)) {
            remoteDataSource.getAllUsersRemote(currentPage)
        } else {
            localDataSource.getAllUsersLocal().toLiveData()
        }

    override fun saveUsers(users: List<User>) {
        try {
            localDataSource.saveUsersLocal(users)
        } catch (e: Exception) {
            Log.d("TAG", "error storing in db $e")
        }
    }
}