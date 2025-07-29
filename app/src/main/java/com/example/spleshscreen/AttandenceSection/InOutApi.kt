package com.example.spleshscreen.AttandenceSection

import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.Body

import retrofit2.http.POST

interface InOutApi {
    @POST("in-out-detail")
    suspend fun getInOutDetails(@Body request: EmployeeRequest): InOutDataX
}
