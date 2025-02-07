package com.example.employeedirectoryapp.network.retrofit

import com.krupa.employeelistapp.model.NetworkResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * Custom Retrofit CallAdapter class to delegate to call
 */
class NetworkResultCallAdapter(
    private val resultType: Type
): CallAdapter<Type, Call<NetworkResult<Type>>> {
    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<NetworkResult<Type>> = NetworkResultCall(call)
}