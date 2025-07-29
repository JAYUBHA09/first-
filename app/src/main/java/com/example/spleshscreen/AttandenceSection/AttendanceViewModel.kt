package com.example.spleshscreen.AttandenceSection

import android.os.Build
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spleshscreen.RetrofitInstance
import com.example.spleshscreen.UserDetailApi.UserPreferences
import com.example.spleshscreen.UserDetailApi.getuserDetail.Data
import kotlinx.coroutines.launch



class AttendanceViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    var inOutDetail by mutableStateOf<InOutResponse?>(null)
        private set

    var report by mutableStateOf<Reports?>(null)
        private set

    fun LoadInOutDetailsForCurrentUser() {
        viewModelScope.launch {
            val userDetails = userPreferences.getUserDetails()

            if (userDetails != null) {
                loadInOutDetail(userDetails.id) // suspend function now
            } else {
                errorMessage = "User not logged in"
            }
        }
    }
    private suspend fun loadInOutDetail(employeeId: Int) {
        isLoading = true
        try {
            val response = RetrofitInstance.inOutApi.getInOutDetails(
                EmployeeRequest(employeeId.toString(), "2025-07-28")
            )

            val body = response.data
            if (response.status == "Success" && body != null) {
                inOutDetail = body
                report = body.reports
            } else {
                val errorMsg = response.message ?: "Something went wrong"
                errorMessage = errorMsg
            }

        } catch (e: Exception) {
            val errorMsg = when (e) {
                is java.net.UnknownHostException -> "No internet connection"
                is java.net.SocketTimeoutException -> "Request timeout"
                else -> "Unexpected error: ${e.localizedMessage}"
            }
            Log.e("AttendanceViewModel", "Exception during in-out fetch", e)
            errorMessage = errorMsg
        } finally {
            isLoading = false
        }
    }

    fun clearError() {
        if (errorMessage.isNullOrEmpty()) {
            errorMessage = ""
        }
    }
}