package com.example.cleanarchitecture.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    // Function to update loading state
    protected fun setLoadingState(loading: Boolean) {
        isLoading.value = loading
    }
}