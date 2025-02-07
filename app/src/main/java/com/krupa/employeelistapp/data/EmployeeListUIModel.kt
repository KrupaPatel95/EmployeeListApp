package com.krupa.employeelistapp.data

data class EmployeeListUIModel(
    val list: List<Employee> = listOf(),
    val errorMessage: String = ""
)
