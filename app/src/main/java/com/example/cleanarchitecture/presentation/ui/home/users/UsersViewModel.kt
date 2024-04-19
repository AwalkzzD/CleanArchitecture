package com.example.cleanarchitecture.presentation.ui.home.users

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.base.viewmodel.BaseViewModel
import com.example.cleanarchitecture.data.dto.user.User
import com.example.cleanarchitecture.domain.usecase.user.GetAllUsers
import com.example.cleanarchitecture.domain.usecase.user.SaveUsers

class UsersViewModel(private val getAllUsers: GetAllUsers, private val saveUsers: SaveUsers) :
    BaseViewModel() {

    val isLoading = ObservableBoolean()

    var usersLiveData: MutableLiveData<List<User>> = MutableLiveData()
    private var currentPage = 0
    private var perPage = 3

    fun getUsers() {
        isLoading.set(true)
        usersLiveData = getAllUsers.invoke(currentPage + 1, perPage) as MutableLiveData<List<User>>
        currentPage = currentPage.inc()
    }

    fun saveUsers(users: List<User>) {
        saveUsers.invoke(users)
    }

    fun resetCurrentPage() {
        currentPage = 0
    }

}
