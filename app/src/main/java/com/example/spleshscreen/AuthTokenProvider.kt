package com.example.spleshscreen

import android.content.Context
import com.example.spleshscreen.UserDetailApi.UserPreferences
import kotlinx.coroutines.runBlocking

object AuthTokenProvider {
    private var userPreferences: UserPreferences? = null

    fun init(context: Context){
        userPreferences = UserPreferences(context)
    }
    fun getToken(): String? {
        return runBlocking {
            userPreferences?.getToken()
        }
    }
}