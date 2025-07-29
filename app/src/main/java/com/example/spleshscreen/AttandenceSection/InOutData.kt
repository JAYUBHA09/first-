package com.example.spleshscreen.AttandenceSection

import kotlinx.serialization.Serializable

@Serializable
data class EmployeeRequest(
    val employee_id : String,
    val punch_date: String
)
