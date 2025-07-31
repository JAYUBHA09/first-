package com.example.spleshscreen

import AuthApi
import androidx.compose.ui.platform.LocalContext
import com.example.spleshscreen.AttandenceSection.InOutApi
import com.example.spleshscreen.UserDetailApi.UserPreferences
import java.util.concurrent.TimeUnit
import com.example.spleshscreen.UserDetailApi.getuserDetail.MeApi
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


object RetrofitInstance {
    private const val BASE_URL = "https://upbeat-hertz.165-22-65-20.plesk.page/api/v1/"
    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val gson = GsonBuilder()
        .setLenient()
        .create()

    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor ( AuthInterceptor{
            AuthTokenProvider.getToken()
        } )
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val authApi: AuthApi = retrofit.create(AuthApi::class.java)
    val meApi: MeApi = retrofit.create(MeApi::class.java)

    val inOutApi: InOutApi by lazy {
        retrofit.create(InOutApi::class.java)
    }
}
