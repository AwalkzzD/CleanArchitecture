package com.example.cleanarchitecture.base.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.data.dto.user.User
import com.example.cleanarchitecture.data.local.model.get_users.UserEntity
import com.example.cleanarchitecture.data.remote.model.get_users.Data

fun <T> List<T>.toLiveData(): LiveData<List<T>> {
    val liveData = MutableLiveData<List<T>>()
    liveData.value = this
    return liveData
}

fun UserEntity.toUser(): User = User(
    id = id, avatar = avatar, email = email, firstName = firstName, lastName = lastName
)

fun User.toUserEntity(): UserEntity = UserEntity(
    id = id, avatar = avatar, email = email, firstName = firstName, lastName = lastName
)

fun Data.toUser(): User = User(
    id = id, avatar = avatar, email = email, firstName = firstName, lastName = lastName
)

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