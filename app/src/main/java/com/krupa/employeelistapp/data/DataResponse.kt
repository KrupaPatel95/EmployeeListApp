package com.krupa.employeelistapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataResponse(
    @field:Json(name = "employees") val employees: List<Employee>
)

@JsonClass(generateAdapter = true)
data class Employee(
    @field:Json(name = "uuid") val uuid: String,
    @field:Json(name = "full_name") val full_name: String,
    @field:Json(name = "email_address") val email_address: String,
    @field:Json(name = "team") val team: String,
    @field:Json(name = "employee_type") val employee_type: String,
    @field:Json(name = "biography") val biography: String? ="",
    @field:Json(name = "photo_url_large") val photo_url_large: String? = "",
    @field:Json(name = "photo_url_small") val photo_url_small: String? = "",
    @field:Json(name = "phone_number") val phone_number: String? = "",
)