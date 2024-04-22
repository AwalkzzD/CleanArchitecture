package com.example.cleanarchitecture.presentation.ui.home.users

import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.base.viewmodel.BaseViewModel
import com.example.cleanarchitecture.data.dto.user.User
import com.example.cleanarchitecture.domain.usecase.user.GetAllUsers
import com.example.cleanarchitecture.domain.usecase.user.SaveUsers

class UsersViewModel(private val getAllUsers: GetAllUsers, private val saveUsers: SaveUsers) :
    BaseViewModel() {

    var usersLiveData: MutableLiveData<List<User>> = MutableLiveData()
    private var currentPage = 0
    private var perPage = 3

    fun getUsers() {
        startLoading()
        usersLiveData = getAllUsers.invoke(currentPage + 1, perPage) as MutableLiveData<List<User>>
    }

    fun saveUsers(users: List<User>) {
        saveUsers.invoke(users)
    }

    fun resetCurrentPage() {
        currentPage = 0
    }

    fun addCurrentPage() {
        currentPage = currentPage.inc()
    }
}
