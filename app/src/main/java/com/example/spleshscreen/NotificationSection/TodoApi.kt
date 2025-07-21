package com.example.spleshscreen.NotificationSection

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TodoApi{
    @GET("todos")
    suspend fun getTodos() : TodoResponse
}


object RetrofitInstance{
    private const val BASE_URL = "https://dummyjson.com/"

    val api : TodoApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(TodoApi::class.java)
    }
}