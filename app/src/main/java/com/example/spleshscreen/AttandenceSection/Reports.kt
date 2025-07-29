package com.example.spleshscreen.AttandenceSection

data class Reports(
    val absent: Int,
    val alldata: List<Any>,
    val employee_id: String,
    val halfday: Int,
    val pressent: Int,
    val punch_date: String,
    val total: Int,
    val total_week_working_time: String,
    val total_working_time: String,
    val working_hours: String
)