package com.example.cleanarchitecture.framework.data.remote.network.api_service

import com.example.cleanarchitecture.framework.data.remote.network.entity.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetAllUsers {

    @GET("/api/users")
    fun getAllUsers(@Query("page") page: Int): Call<UserResponse>

}