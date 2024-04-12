package com.example.cleanarchitecture.presentation.ui.home.users

import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.core.data.User
import com.example.cleanarchitecture.core.usecase.GetAllUsers
import com.example.d2m.screens.utils.base_classes.BaseViewModel

class UsersViewModel(getAllUsers: GetAllUsers) : BaseViewModel() {

    var usersLiveData: MutableLiveData<List<User>> = getAllUsers.invoke() as MutableLiveData<List<User>>
}