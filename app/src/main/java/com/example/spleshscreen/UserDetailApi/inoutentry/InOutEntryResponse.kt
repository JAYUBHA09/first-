package com.example.spleshscreen.UserDetailApi.inoutentry

import kotlinx.serialization.Serializable


@Serializable
data class InOutEntryResponse(
    val status: String,
    val message: String,
    val data: InOutEntryData
)

@Serializable
data class InOutEntryData(
    val id: Int,
    val employee_id: Int,
    val punch_in_time: String,
    val punch_out_time: String? = null,
    val punch_date: String,
    val punch_type: Int,
    val punch_location: String,
    val qrcode_hash: String,
    val is_manual: Int,
    val is_left: Int,
    val created_at: String,
    val updated_at: String
)
