package com.ssmmhh.doeit.taskdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ssmmhh.doeit.ServiceLocator
import com.ssmmhh.doeit.TaskDetail
import com.ssmmhh.doeit.data.Task
import com.ssmmhh.doeit.data.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val taskRepository: TaskRepository = ServiceLocator.taskRepository

    private val taskId = savedStateHandle.toRoute<TaskDetail>().taskId

    private val _task: MutableStateFlow<Task?> = MutableStateFlow(value = null)
    val task: StateFlow<Task?> = _task

    init {
        fetchTask()
    }

    private fun fetchTask() {
        viewModelScope.launch {
            _task.update { taskRepository.getTaskById(taskId) }
        }
    }
}