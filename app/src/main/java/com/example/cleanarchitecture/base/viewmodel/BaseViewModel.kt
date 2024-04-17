package com.example.cleanarchitecture.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "BaseViewModel"

open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    // function to update loading state
    protected fun setLoadingState(loading: Boolean) {
        isLoading.value = loading
    }
}