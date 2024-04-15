package com.example.cleanarchitecture.data.remote.api

import com.example.cleanarchitecture.data.remote.model.get_users.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetAllUsers {

    @GET("/api/users")
    fun getAllUsers(@Query("page") page: Int): Call<UserResponse>

}