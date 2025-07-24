package com.example.spleshscreen.UserDetailApi


import kotlinx.serialization.Serializable
@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
@Serializable
data class LoginResponse(
    val status: String,
    val message: String,
    val data: LoginData
)
@Serializable
data class LoginData(
    val access_token: String,
    val token_type: String,
    val user_details: UserDetails
)

@Serializable
data class UserDetails(
    val id: Int,
    val user_id: Int,
    val company_id: Int,
    val designation_id: Int,
    val department_id: Int,
    val firstname: String? = null,
    val lastname: String? = null,
    val middlename: String? = null,
    val permanent_address: String? = null,
    val picture: String? = null,
    val birthdate: String? = null,
    val joindate: String? = null,
    val is_trainee: Int,
    val trainee_full_day_date: String? = null,
    val pan: String? = null,
    val uan: String? = null,
    val bank_ac_number: String? = null,
    val bank_name: String? = null,
    val punch_type: Int,
    val status: Int,
    val last_working_day: String? = null,
    val increment: String? = null,
    val salary: String? = null,
    val reson_for_leave: String? = null,
    val employee_aadhar_card: String? = null,
    val employee_pan_card: String? = null,
    val reporting_to: String? = null,
    val contact_number: String? = null,
    val emergency_contact_person_name: String? = null,
    val emergency_contact_person_relation: String? = null,
    val emergency_contact_person_number: String? = null,
    val temporary_address: String? = null,
    val skype_id: String? = null,
    val preferred_time: String? = null,
    val count_preferred_time: Int,
    val deleted_at: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val is_admin: String? = null
)



