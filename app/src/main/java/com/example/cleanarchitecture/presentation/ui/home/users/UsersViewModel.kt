package com.example.cleanarchitecture.presentation.ui.home.users

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.core.data.User
import com.example.cleanarchitecture.core.usecase.GetAllUsers
import com.example.cleanarchitecture.core.usecase.SaveUsers
import com.example.cleanarchitecture.presentation.utils.base_classes.BaseViewModel

class UsersViewModel(private val getAllUsers: GetAllUsers, private val saveUsers: SaveUsers) :
    BaseViewModel() {

    val usersLiveData: MutableLiveData<List<User>> = MutableLiveData()

    fun getUsers() {
        usersLiveData.postValue(getAllUsers.invoke())
    }

    fun saveUsers(users: List<User>) {
        Log.d("TAG", "save users data -> $users ")
        saveUsers.invoke(users)
    }

}