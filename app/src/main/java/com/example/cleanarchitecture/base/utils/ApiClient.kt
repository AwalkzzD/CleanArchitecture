package com.example.cleanarchitecture.base.utils

import com.example.cleanarchitecture.base.utils.AppConstants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private var retrofitClient: Retrofit? = null

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(AppConstants.CONNECTION_TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
        .readTimeout(AppConstants.READ_TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
        .writeTimeout(AppConstants.WRITE_TIME_OUT.toLong(), TimeUnit.MILLISECONDS).build()

    private fun getRetrofitClient(): Retrofit = (retrofitClient) ?: run {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(httpClient).build().apply { retrofitClient = this }
    }

    fun <S> createService(serviceClass: Class<S>): S = getRetrofitClient().create(serviceClass)

    fun destroyInstance() {
        retrofitClient = null
    }
}