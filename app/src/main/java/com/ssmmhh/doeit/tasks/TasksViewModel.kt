package com.ssmmhh.doeit.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssmmhh.doeit.data.InMemoryTaskRepository
import com.ssmmhh.doeit.data.Task
import com.ssmmhh.doeit.data.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TasksViewModel : ViewModel() {

    private val taskRepository: TaskRepository = InMemoryTaskRepository()

    val todayTask: StateFlow<List<Task>> = taskRepository.fetchTodayTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = listOf()
        )

}