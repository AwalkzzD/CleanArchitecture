package com.example.cleanarchitecture.presentation.ui.home.users

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.base.viewmodel.BaseViewModel
import com.example.cleanarchitecture.data.dto.user.User
import com.example.cleanarchitecture.domain.usecase.user.GetAllUsers
import com.example.cleanarchitecture.domain.usecase.user.SaveUsers

class UsersViewModel(private val getAllUsers: GetAllUsers, private val saveUsers: SaveUsers) :
    BaseViewModel() {

    var usersLiveData: MutableLiveData<List<User>> = MutableLiveData()
    private var currentPage = 0

    fun getUsers() {
        setLoadingState(true)
        currentPage = currentPage.inc()
        usersLiveData = getAllUsers.invoke(currentPage) as MutableLiveData<List<User>>
        setLoadingState(false)
    }

    fun saveUsers(users: List<User>) {
        Log.d("TAG", "save users data -> $users ")
        saveUsers.invoke(users)
    }
}
