package com.example.cleanarchitecture.presentation.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cleanarchitecture.presentation.utils.base_classes.BaseViewModel

inline fun <reified T : BaseViewModel> AppCompatActivity.obtainViewModel(): Lazy<T> =
    lazy { ViewModelProvider(this, ViewModelFactory.getInstance(application))[T::class.java] }

inline fun <reified T : BaseViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>): T =
    ViewModelProvider(this, ViewModelFactory.getInstance(application))[viewModelClass]

inline fun <reified T : BaseViewModel> FragmentActivity.obtainViewModel(): Lazy<T> =
    lazy { ViewModelProvider(this, ViewModelFactory.getInstance(application))[T::class.java] }

inline fun <reified T : BaseViewModel> FragmentActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProvider(this, ViewModelFactory.getInstance(application))[viewModelClass]
