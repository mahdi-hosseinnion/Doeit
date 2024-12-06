package com.ssmmhh.doeit.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

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

    override suspend fun addTask(task: Task) {
        tasks.update {
            it.toMutableList().apply {
                add(task)
            }
        }
    }

    override suspend fun toggleTaskIsComplete(task: Task) {
        tasks.update { tasksList ->
            val newTask = task.copy(isCompleted = !task.isCompleted)
            val taskIndex = tasksList.indexOfFirst { it.id == task.id }
            if (taskIndex < 0) return@update tasksList
            tasksList.toMutableList().apply {
                set(taskIndex, newTask)
            }
        }
    }
}