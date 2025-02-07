package com.krupa.employeelistapp.util

import com.krupa.employeelistapp.model.NetworkResult
import retrofit2.HttpException
import retrofit2.Response

fun <T : Any> handleApi(
    execute: () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.Error(response.code(), response.message())
        }
    } catch (e: HttpException) {
        NetworkResult.Error(e.code(), e.message())
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }
}