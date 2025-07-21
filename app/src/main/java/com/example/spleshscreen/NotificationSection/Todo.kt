package com.example.spleshscreen.NotificationSection

data class Todo(
    val todo: String,
    val completed: Boolean,
    val userId: Int
)

data class TodoResponse(
    val todos : List<Todo>
)
