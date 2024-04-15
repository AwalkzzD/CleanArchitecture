package com.example.cleanarchitecture.base.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.domain.repository.UserRepositoryImpl
import com.example.cleanarchitecture.domain.usecase.user.GetAllUsers
import com.example.cleanarchitecture.domain.usecase.user.SaveUsers
import com.example.cleanarchitecture.data.repository.user.UserLocalDataSourceImpl
import com.example.cleanarchitecture.base.utils.UserAppDatabase
import com.example.cleanarchitecture.data.repository.user.UserRemoteDataSourceImpl
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