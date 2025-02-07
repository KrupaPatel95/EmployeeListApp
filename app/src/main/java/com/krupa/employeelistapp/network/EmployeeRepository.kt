package com.krupa.employeelistapp.network

import com.krupa.employeelistapp.data.DataResponse
import com.krupa.employeelistapp.model.NetworkResult
import javax.inject.Inject

class EmployeeRepository
@Inject constructor(
    private val employeeService: EmployeeService
) {
    suspend fun getEmployee(): NetworkResult<DataResponse> {
        return employeeService.getEmployees()
    }
}