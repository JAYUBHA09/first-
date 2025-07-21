package com.example.spleshscreen.UserDetailApi

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spleshscreen.RetrofitInstance
import kotlinx.coroutines.launch


class AuthViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var loginSuccess by mutableStateOf(false)

        private set

    var errorMessage by mutableStateOf("")
        private set

    var user by mutableStateOf<UserDetails?>(null)
        private set


    var token by mutableStateOf("")
        private set




    fun login(email: String, password: String ,prefs: UserPreferences) {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = RetrofitInstance.authApi.login(LoginRequest(email, password))
                Log.d("RawLoginData", response.data.toString())

                if (response.status == "Success") {
                    val accessToken = response.data.access_token
                    val tokenType = response.data.token_type

                    token = accessToken
                    user = response.data.user_details
                    loginSuccess = true
                    errorMessage = ""

                    Log.d("AuthViewModel", "Login successful: ${response.message}")

                    // Save token to DataStore
                    prefs.saveToken(accessToken, tokenType)
                    Log.d("AuthViewModel" , "Token saved to DataStore : $accessToken")

                }
                else {
                    errorMessage = response.message
                }
            }
            catch (e: Exception) {
                errorMessage = e.message ?: "Unknown error"
            }
            finally {
                isLoading = false
            }
        }
    }

    fun logout(prefs: UserPreferences) {
        viewModelScope.launch {
            prefs.clearToken()
            loginSuccess = false
            token = ""
            user = null
        }
    }

    fun loadUserFromToken(token: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                val userDetails = RetrofitInstance.meApi.getUserDetails("Bearer $token")
                user = userDetails
            } catch (e: Exception) {
                errorMessage = "Failed to load user: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}

