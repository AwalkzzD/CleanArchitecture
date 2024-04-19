package com.example.cleanarchitecture.data.repository.user

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.cleanarchitecture.base.extensions.toUser
import com.example.cleanarchitecture.base.utils.ApiClient
import com.example.cleanarchitecture.data.dto.user.User
import com.example.cleanarchitecture.data.remote.api.GetAllUsers
import com.example.cleanarchitecture.data.remote.model.get_users.Data
import com.example.cleanarchitecture.data.remote.model.get_users.UserResponse
import com.example.cleanarchitecture.domain.repository.UserRemoteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "UserRemoteDataSourceImp"

class UserRemoteDataSourceImpl : UserRemoteDataSource {

    private val userData: MutableLiveData<List<Data>> = MutableLiveData()

    override fun getAllUsersRemote(currentPage: Int, perPage: Int): LiveData<List<User>> {
        Handler(Looper.getMainLooper()).postDelayed({
            val retrofitInstance = ApiClient.createService(GetAllUsers::class.java)
            val retrofitData = retrofitInstance.getAllUsers(currentPage, perPage)
            retrofitData.enqueue(object : Callback<UserResponse?> {
                override fun onResponse(
                    call: Call<UserResponse?>,
                    response: Response<UserResponse?>
                ) {
                    userData.postValue(response.body()?.data ?: emptyList())
                }

                override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                    userData.postValue(emptyList())
                }
            })

            ApiClient.destroyInstance()
        }, 2000)

        return userData.map { entities ->
            entities.map {
                it.toUser()
            }
        }
    }

}