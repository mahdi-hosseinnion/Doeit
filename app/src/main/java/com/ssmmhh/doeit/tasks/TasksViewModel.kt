package com.ssmmhh.doeit.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssmmhh.doeit.ServiceLocator
import com.ssmmhh.doeit.data.Task
import com.ssmmhh.doeit.data.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TasksViewModel : ViewModel() {

    private val taskRepository: TaskRepository = ServiceLocator.taskRepository

    val todayTask: StateFlow<List<Task>> = taskRepository.fetchTodayTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = listOf()
        )

    fun toggleTaskIsComplete(task: Task) {
        viewModelScope.launch {
            taskRepository.toggleTaskIsComplete(task)
        }
    }
}