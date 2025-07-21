package com.example.spleshscreen

import AuthApi
import MeApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://upbeat-hertz.165-22-65-20.plesk.page/api/v1/"

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val authApi: AuthApi = retrofit.create(AuthApi::class.java)
    val meApi: MeApi = retrofit.create(MeApi::class.java)
}
