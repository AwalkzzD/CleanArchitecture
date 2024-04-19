package com.example.cleanarchitecture.base.utils

class Result<out T>(val status: Status, val data: T?) {
    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(Status.SUCCESS, data)
        }

        fun <T> loading(): Result<T> {
            return Result(Status.LOADING, null)
        }

        fun <T> error(): Result<T> {
            return Result(Status.ERROR, null)
        }
    }
}

