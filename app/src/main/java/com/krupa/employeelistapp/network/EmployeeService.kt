package com.krupa.employeelistapp.network

import com.krupa.employeelistapp.data.DataResponse
import com.krupa.employeelistapp.model.NetworkResult
import retrofit2.http.GET

interface EmployeeService {
    @GET("employees.json")
    suspend fun getEmployees(): NetworkResult<DataResponse>
}