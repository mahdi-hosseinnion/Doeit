package com.ssmmhh.doeit

import com.ssmmhh.doeit.data.InMemoryTaskRepository
import com.ssmmhh.doeit.data.TaskRepository

object ServiceLocator {

    val taskRepository: TaskRepository by lazy {
        InMemoryTaskRepository()
    }
}