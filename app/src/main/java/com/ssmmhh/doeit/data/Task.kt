package com.ssmmhh.doeit.data

import kotlinx.serialization.Serializable
import java.util.UUID

data class Task(
    val title: String,
    val isCompleted: Boolean = false,
    val note: String = "",
    val id: TaskId = TaskId(UUID.randomUUID().toString())
)

@JvmInline
@Serializable
value class TaskId(val value: String)