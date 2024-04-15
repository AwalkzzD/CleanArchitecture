package com.example.cleanarchitecture.presentation.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.core.domain.UserRepositoryImpl
import com.example.cleanarchitecture.core.usecase.GetAllUsers
import com.example.cleanarchitecture.core.usecase.SaveUsers
import com.example.cleanarchitecture.framework.data.local.UserLocalDataSourceImpl
import com.example.cleanarchitecture.framework.data.local.db.UserAppDatabase
import com.example.cleanarchitecture.framework.data.remote.UserRemoteDataSourceImpl
import com.example.cleanarchitecture.presentation.ui.home.users.UsersViewModel

class ViewModelFactory(
    private val mApplication: Application
) : ViewModelProvider.Factory {

    private val userAppDatabase: UserAppDatabase by lazy {
        UserAppDatabase.getInstance(mApplication)
    }

    private val userRepository: UserRepositoryImpl by lazy {
        UserRepositoryImpl(
            UserLocalDataSourceImpl(userAppDatabase.userDao()), UserRemoteDataSourceImpl()
        )
    }

    private val getAllUsers: GetAllUsers by lazy {
        GetAllUsers(userRepository)
    }

    private val saveUsers: SaveUsers by lazy {
        SaveUsers(userRepository)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(UsersViewModel::class.java) -> {
                UsersViewModel(getAllUsers, saveUsers)
            }

            else -> {
                throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
            }
        }

    } as T

    companion object {
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                ViewModelFactory(
                    application
                ).apply { INSTANCE = this }
            }
    }
}