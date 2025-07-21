package com.example.spleshscreen.NotificationSection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {

    var todoList by mutableStateOf<List<Todo>>(emptyList())

    var errorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    init {
        getTodos()
    }

    private fun getTodos(){
        viewModelScope.launch {
            isLoading = true
            val apiService = RetrofitInstance.api
            try{
                val todos = apiService.getTodos()
                todoList = todos.todos
            }catch (e: Exception){
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }

}