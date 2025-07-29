package com.example.spleshscreen.LeaveSection.LeaveApi

data class LeaveResponse(
    val `data`: leaveData,
    val message: String,
    val status: String
)