package com.example.spleshscreen

import AuthApi
import com.example.spleshscreen.AttandenceSection.InOutApi
import java.util.concurrent.TimeUnit
import com.example.spleshscreen.UserDetailApi.getuserDetail.MeApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


object RetrofitInstance {
    private const val BASE_URL = "https://upbeat-hertz.165-22-65-20.plesk.page/api/v1/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authApi: AuthApi = retrofit.create(AuthApi::class.java)
    val meApi: MeApi = retrofit.create(MeApi::class.java)

    val inOutApi: InOutApi = retrofit.create(InOutApi::class.java)
}
