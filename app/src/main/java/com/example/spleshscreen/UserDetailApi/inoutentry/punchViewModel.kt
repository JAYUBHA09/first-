//package com.example.spleshscreen.UserDetailApi.inoutentry
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.spleshscreen.AttandenceSection.InOutApi
//import com.example.spleshscreen.AttandenceSection.InOutDetailData
//import com.example.spleshscreen.AttandenceSection.InOutItem
//import com.example.spleshscreen.RetrofitInstance
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class punch @Inject constructor() : ViewModel() {
//
//    var state by mutableStateOf(inOutState())
//        private set
//
//    var inOutData by mutableStateOf<List<InOutItem>>(emptyList())
//
//    fun loadInOutData(employeeId: String){
//        viewModelScope.launch {
//            state.isLoading = true
//            try {
//                val response = RetrofitInstance.inOutApi.getInOutDetails(employeeId)
//                if (response.status == "Success"){
//                    inOutData = response.data.in_out_data
//                    val state = state.copy(
//                        isSuccess = true,
//                        isLoading = false
//                    )
//                }else {
//                    state = state.copy(isLoading = false, errorMessage = response.message)
//                }
//            }catch (e: Exception){
//                state = state.copy(isLoading = false
//                ,isSuccess = false
//                , errorMessage = "Something went wrong ${e.message}")
//            }
//        }
//    }
//
//    data class inOutState(
//        var isLoading: Boolean = false,
//        var isSuccess: Boolean = false,
//        var errorMessage: String = ""
//    )
//}