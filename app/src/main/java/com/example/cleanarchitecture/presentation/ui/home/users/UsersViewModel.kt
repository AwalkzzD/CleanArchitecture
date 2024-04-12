package com.example.cleanarchitecture.presentation.ui.home.users

import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.core.data.User
import com.example.cleanarchitecture.core.domain.UserRepository
import com.example.cleanarchitecture.presentation.utils.base_classes.BaseViewModel

class UsersViewModel(userRepository: UserRepository) : BaseViewModel() {

    val usersLiveData: MutableLiveData<List<User>> =
        userRepository.getAllUsers() as MutableLiveData<List<User>>
}