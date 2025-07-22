package com.example.spleshscreen.UserDetailApi.getuserDetail

data class Data(
    val access_token: String,
    val api_token: Any,
    val created_at: String,
    val designation_id: Int,
    val designation_name: String,
    val email: String,
    val email_verified_at: Any,
    val id: Int,
    val is_admin: Any,
    val name: String,
    val otp_verified: Int,
    val picture: String,
    val punch_in_time: String,
    val punch_time: String,
    val punch_type: Int,
    val punch_type_name: String,
    val total_time: String,
    val updated_at: String,
    val week: Week,
    val workstacker_access_token: String,
    val workstacker_api_base_url: String
)