package com.example.spleshscreen.TaskScreens

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id : Int,
    val task: String,
    val priority: String,
    val status: String,
    val date: String,
    val icon: Int
): Parcelable

