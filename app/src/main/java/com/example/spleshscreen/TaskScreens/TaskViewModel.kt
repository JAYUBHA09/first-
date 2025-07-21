package com.example.spleshscreen.TaskScreens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spleshscreen.Navigation.Screens
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val _selectedTask = mutableStateOf<Task?>(null)
    val selectedTask: State<Task?> = _selectedTask


    fun selectedTask(task: Task){
        _selectedTask.value = task
    }

}