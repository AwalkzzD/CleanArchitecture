package com.example.cleanarchitecture.framework.data.remote

import com.example.cleanarchitecture.core.data.User
import com.example.cleanarchitecture.core.domain.UserRemoteDataSource
import com.example.cleanarchitecture.framework.data.remote.network.ApiClient
import com.example.cleanarchitecture.framework.data.remote.network.api_service.GetAllUsers
import com.example.cleanarchitecture.framework.data.remote.network.entity.Data
import com.example.cleanarchitecture.framework.data.remote.network.entity.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "UserRemoteDataSourceImp"

class UserRemoteDataSourceImpl : UserRemoteDataSource {

    private val userData: MutableList<Data> = mutableListOf()

    private fun Data.toUser(): User = User(
        id = id, avatar = avatar, email = email, firstName = firstName, lastName = lastName
    )

    override fun getAllUsersRemote(): List<User> {
        val retrofitInstance = ApiClient.createService(GetAllUsers::class.java) as GetAllUsers
        val retrofitData = retrofitInstance.getAllUsers(1)
        retrofitData.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                userData.addAll(response.body()?.data ?: emptyList())
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                userData.addAll(emptyList())
            }
        })

        return userData.map {
            it.toUser()
        }
    }
}