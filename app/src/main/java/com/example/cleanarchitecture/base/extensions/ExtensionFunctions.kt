package com.example.cleanarchitecture.base.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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

fun <T> LiveData<List<T>>.toList(): List<T> {
    return this.value ?: emptyList()
}

object NetworkUtils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

                else -> false
            }
        }
        return false
    }
}