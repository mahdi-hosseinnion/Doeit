package com.ssmmhh.doeit.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun fetchTodayTasks(): Flow<List<Task>>

    suspend fun getTaskById(taskId: String): Task?

    suspend fun addTask(task: Task)
}