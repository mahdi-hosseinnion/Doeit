package com.ssmmhh.doeit.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class InMemoryTaskRepository : TaskRepository {

    private val mockTasks = listOf(
        Task("Design the main screen"),
        Task("Learn about clean architecture"),
        Task("Wash the dishes", true)
    )
    private val tasks: MutableStateFlow<List<Task>> = MutableStateFlow(mockTasks)

    override fun fetchTodayTasks(): Flow<List<Task>> {
        return tasks
    }

    override suspend fun getTaskById(taskId: String): Task? {
        return tasks.value.firstOrNull { it.id == taskId }
    }
}