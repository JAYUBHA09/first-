package com.example.spleshscreen.UserDetailApi

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spleshscreen.RetrofitInstance
import com.example.spleshscreen.UserDetailApi.getuserDetail.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var loginSuccess by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    var user by mutableStateOf<UserDetails?>(null)
        private set

    var userDetails by mutableStateOf<Data?>(null)


    var token by mutableStateOf("")
        private set


    fun login(email: String, password: String ,prefs: UserPreferences) {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = RetrofitInstance.authApi.login(LoginRequest(email, password))
                Log.d("RawLoginData", response.data.toString())

                if (response.status.equals("Success", ignoreCase = true)){
                    val accessToken = response.data.access_token
                    val tokenType = response.data.token_type
                    val userDetail = response.data.user_details

                    token = accessToken
                    user = userDetail
                    loginSuccess = true
                    errorMessage = ""

                    Log.d("AuthViewModel", "Login successful: ${response.message}")

                    // Save token to DataStore
                    prefs.saveToken(accessToken, tokenType)
                    Log.d("AuthViewModel" , "Token saved to DataStore : $accessToken")

                    if (response.status == "Success") {
                        val userDetails = response.data.user_details
                        viewModelScope.launch {
                            prefs.saveUserDetails(userDetails)
                        }
                    }
                    Log.d("firstname", user?.firstname ?: "null")
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
            prefs.clearUserDetails()
            Log.d("Logout", "Cleared token and user details")
            loginSuccess = false
            token = ""
            user = null
        }
    }
    fun fetchUserDetails(prefs: UserPreferences){
        viewModelScope.launch {
            isLoading = true
            try {
                val token = prefs.getToken()
                val response = RetrofitInstance.meApi.userDetail()
                Log.d("Token","Loaded : $token")

                if (response.status.equals("Success", ignoreCase = true)){
                    userDetails = response.data
                    Log.d("User", "Loaded: ${userDetails?.name}")
                }

            }catch (e: Exception){
                errorMessage = "Error fetching user details"
            }finally {
                isLoading = false
            }
        }
    }

  fun loadUserDetails(prefs: UserPreferences){
        viewModelScope.launch {
            try {
                val userDetails = prefs.getUserDetails()
                if (userDetails != null){
                    user = userDetails
                    token = prefs.getToken()
                    loginSuccess = true
                    Log.d("UserFirstName","Loaded: ${user?.firstname}")
                }
            }catch (e: Exception){
                Log.e("AuthViewModel", "Error loading user details",e)
            }
        }
    }
}