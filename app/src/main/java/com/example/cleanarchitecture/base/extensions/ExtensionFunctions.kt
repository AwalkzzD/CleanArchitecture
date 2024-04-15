package com.example.cleanarchitecture.base.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.base.viewmodel.BaseViewModel
import com.example.cleanarchitecture.base.viewmodel.ViewModelFactory

inline fun <reified T : BaseViewModel> AppCompatActivity.obtainViewModel(): Lazy<T> =
    lazy { ViewModelProvider(this, ViewModelFactory.getInstance(application))[T::class.java] }

inline fun <reified T : BaseViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>): T =
    ViewModelProvider(this, ViewModelFactory.getInstance(application))[viewModelClass]

inline fun <reified T : BaseViewModel> FragmentActivity.obtainViewModel(): Lazy<T> =
    lazy { ViewModelProvider(this, ViewModelFactory.getInstance(application))[T::class.java] }

inline fun <reified T : BaseViewModel> FragmentActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProvider(this, ViewModelFactory.getInstance(application))[viewModelClass]

fun <T> List<T>.toLiveData(): LiveData<List<T>> {
    val liveData = MutableLiveData<List<T>>()
    liveData.value = this
    return liveData
}