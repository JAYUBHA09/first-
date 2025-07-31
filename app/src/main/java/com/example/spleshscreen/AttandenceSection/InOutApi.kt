package com.example.spleshscreen.AttandenceSection

import InOutResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

interface InOutApi {
    @POST("in-out-detail")
    suspend fun getInOutDetails(
        @Body request: EmployeeRequest
    ): Response<InOutResponse>
}

data class EmployeeRequest(
    val employee_id: String?,
    val punch_date: String?
)