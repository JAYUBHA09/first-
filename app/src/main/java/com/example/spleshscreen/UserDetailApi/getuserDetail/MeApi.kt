package com.example.spleshscreen.UserDetailApi.getuserDetail

import com.example.spleshscreen.UserDetailApi.LoginResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface MeApi {
    @GET("me")
    suspend fun userDetail(@Header("Authorization") token: String): userResponse
}