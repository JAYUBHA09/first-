package com.example.spleshscreen.UserDetailApi


import kotlinx.serialization.Serializable

data class LoginRequest(
    val email: String,
    val password: String
)
data class LoginResponse(
    val status: String,
    val message: String,
    val data: LoginData
)

data class LoginData(
    val access_token: String,
    val token_type: String,
    val user_details: UserDetails
)


data class UserDetails(
    val id: Int,
    val user_id: Int,
    val compnay_id: Int,
    val designation_id: Int,
    val department_id: Int,
    val firstname: String,
    val lastname: String,
    val middlename: String,
    val permanent_address: String,
    val picture: String,
    val birthdate: String,
    val joindate: String,
    val is_trainee: Int,
    val trainee_full_day_date: String,
    val pan: String?,
    val uan: String?,
    val bank_ac_number: String?,
    val bank_name: String?,
    val punch_type: Int,
    val status: Int,
    val last_working_day: String?,
    val increment: String?,
    val salary: String,
    val reson_for_leave: String?,
    val employee_aadhar_card: String,
    val employee_pan_card: String,
    val reporting_to: String,
    val contact_number: String,
    val emergency_contact_person_name: String,
    val emergency_contact_person_relation: String,
    val emergency_contact_person_number: String,
    val temporary_address: String,
    val skype_id: String,
    val preferred_time: String,
    val count_preferred_time: Int,
    val deleted_at: String?,
    val created_at: String,
    val updated_at: String,
    val is_admin: String?
)


