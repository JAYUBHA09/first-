package com.example.spleshscreen.LeaveSection.LeaveApi

import com.example.spleshscreen.Navigation.Screens
import retrofit2.http.Body
import retrofit2.http.POST

interface LeaveApi {
    @POST("add_employee_leave")
    suspend fun addLeave(@Body leaveRequest: Screens.LeaveRequest): LeaveResponse
}