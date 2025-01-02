package com.ssmmhh.doeit.data

import android.icu.util.LocaleData
import java.time.Instant
import java.util.UUID

data class Task(
    val title: String,
    val isCompleted: Boolean = false,
    val note: String = "",
    val id: String = UUID.randomUUID().toString(),
    val createdAt: Instant = Instant.now(),
    val dueDate: LocaleData? = null
)
