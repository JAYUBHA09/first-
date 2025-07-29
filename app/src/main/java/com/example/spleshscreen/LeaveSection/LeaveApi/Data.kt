package com.example.spleshscreen.LeaveSection.LeaveApi

data class leaveData(
    val approved_by: Any,
    val approved_date: Any,
    val created_at: String,
    val date_from: String,
    val date_to: String,
    val days: Int,
    val employee: Employee,
    val employee_id: Int,
    val half_day_type: Int,
    val id: Int,
    val is_approved: Int,
    val is_half_day: Int,
    val leave_type: Int,
    val leave_type_name: String,
    val reason: String,
    val updated_at: String
)