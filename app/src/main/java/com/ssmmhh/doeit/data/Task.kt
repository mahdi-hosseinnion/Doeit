package com.ssmmhh.doeit.data

data class Task(
    val title: String,
    val isCompleted: Boolean = false,
    val note:String = ""
)