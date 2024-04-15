package com.example.cleanarchitecture.data.repository.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
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

    private fun Data.toUser(): User = User(
        id = id, avatar = avatar, email = email, firstName = firstName, lastName = lastName
    )

    override fun getAllUsersRemote(): LiveData<List<User>> {
        val retrofitInstance = ApiClient.createService(GetAllUsers::class.java) as GetAllUsers
        val retrofitData = retrofitInstance.getAllUsers(1)
        retrofitData.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                userData.postValue(response.body()?.data ?: emptyList())
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                userData.postValue(emptyList())
            }
        })

        return userData.map { entities ->
            entities.map {
                it.toUser()
            }
        }
    }
}