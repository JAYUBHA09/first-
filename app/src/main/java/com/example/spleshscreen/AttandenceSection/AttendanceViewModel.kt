package com.example.spleshscreen.AttandenceSection

import InOutEntry
import Reports
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spleshscreen.RetrofitInstance
import com.example.spleshscreen.UserDetailApi.UserPreferences
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class AttendanceViewModel : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    var report by mutableStateOf<Reports?>(null)
        private set

    var inOutData by mutableStateOf<List<InOutEntry>>(emptyList())

    fun loadInOutDetailsForCurrentUser(userPreferences: UserPreferences ,selectedDate: String? ) {
        viewModelScope.launch {
            try {
                isLoading = true
               val user = userPreferences.getUserDetails()
                val employeeId = user?.id

                if (employeeId == null) {
                    errorMessage = "User not logged in or employee ID missing"
                    return@launch
                }
                    loadInOutDetail(employeeId.toString(), selectedDate)
                    Log.d("Employ_id", ": ${report?.employee_id}")


            } catch (e: Exception) {
                Log.e("AttendanceViewModel", "Error getting user details", e)
                errorMessage = "Failed to get user information"
            }
            finally {
                isLoading = false
            }
        }
    }
    private suspend fun loadInOutDetail(employee_id: String?, punch_date: String?){
            try {
                val request = EmployeeRequest(
                    employee_id = employee_id,
                    punch_date = punch_date
                )
                val response = RetrofitInstance.inOutApi.getInOutDetails(request)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                if (responseBody != null){
                when (responseBody.status) {
                    "Success" -> {
                        report = responseBody.data.reports
                        inOutData = responseBody.data.in_out_data
                        Log.d("AttendanceViewModel", "Data loaded successfully: ${responseBody.data}")
                    }
                    else -> {
                    errorMessage = responseBody.message.ifEmpty { "Unknown error occurred" }
                    Log.e("AttendanceViewModel", "API failed: ${responseBody.message}")
                }
                }

                }
                else {
                    errorMessage = "Response body is null"
                    Log.e("AttendanceViewModel", "Empty response body")
                }
                } else {
                    val errorBody = response.errorBody()?.string()
                    errorMessage = "Error: ${errorBody ?: "Unknown error"}"
                    Log.e("AttendanceViewModel", "API Error: $errorMessage")
                }

            } catch (e: Exception) {
                Log.e("InOutDataE", "Exception: ${e.message}", e)
            } finally {
                isLoading = false
            }

    }


    fun clearError() {
        errorMessage = ""
    }

    @Composable
    fun RetryLoadData() {
        if (!isLoading) {
            val context = LocalContext.current
            val prefs = UserPreferences(context)
 //           loadInOutDetailsForCurrentUser(prefs)
        }
    }
}