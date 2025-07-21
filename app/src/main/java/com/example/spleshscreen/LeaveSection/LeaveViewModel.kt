package com.example.spleshscreen.LeaveSection


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

enum class LeaveStatus { APPROVED, PENDING }

data class LeaveList(
    val date: LocalDate,
    val type: String,
    val reason: String,
    val status: LeaveStatus
)


@RequiresApi(Build.VERSION_CODES.O)
class LeaveViewModel : ViewModel() {

    private val _leaves = MutableStateFlow(
        listOf(
            LeaveList(LocalDate.of(2025, 6, 12), "Sick Leave", "Fever", LeaveStatus.APPROVED),
            LeaveList(LocalDate.of(2025, 6, 12), "Casual Leave", "Function", LeaveStatus.PENDING),
            LeaveList(LocalDate.of(2025, 7, 10), "Sick Leave", "Flu", LeaveStatus.APPROVED),
            LeaveList(LocalDate.of(2025, 7, 20), "Casual Leave", "Travel", LeaveStatus.PENDING),
            LeaveList(LocalDate.of(2025, 7, 20), "Sick Leave", "Cold", LeaveStatus.APPROVED),
        )
    )
    val leaves: StateFlow<List<LeaveList>> = _leaves

    fun getLeavesByDate(date: LocalDate): List<LeaveList> {
        return _leaves.value.filter { it.date == date }
    }
}
