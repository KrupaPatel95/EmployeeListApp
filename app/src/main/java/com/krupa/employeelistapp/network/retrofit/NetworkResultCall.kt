package com.example.employeedirectoryapp.network.retrofit

import com.krupa.employeelistapp.model.NetworkResult
import com.krupa.employeelistapp.util.handleApi
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Custom Retrofit Call class to delegate Retrofit responses
 */
class NetworkResultCall<T : Any>(
    private val proxy: Call<T>
) : Call<NetworkResult<T>> {

    override fun enqueue(callback: Callback<NetworkResult<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val networkResult = handleApi { response }
                callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val networkResult = NetworkResult.Exception<T>(t)
                callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
            }
        })
    }

    override fun clone(): Call<NetworkResult<T>> = NetworkResultCall(proxy.clone())
    override fun execute(): Response<NetworkResult<T>> = throw NotImplementedError()
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun cancel() = proxy.cancel()
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun request(): Request = proxy.request()
    override fun timeout(): Timeout = proxy.timeout()

}