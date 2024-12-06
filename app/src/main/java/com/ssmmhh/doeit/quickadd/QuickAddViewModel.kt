package com.ssmmhh.doeit.quickadd

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssmmhh.doeit.ServiceLocator
import com.ssmmhh.doeit.data.Task
import com.ssmmhh.doeit.data.TaskRepository
import kotlinx.coroutines.launch

class QuickAddViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val taskRepository: TaskRepository = ServiceLocator.taskRepository

    val taskTitle = savedStateHandle.getStateFlow(key = TASK_TITLE_KEY, initialValue = "")

    fun onTaskTitleChanged(title: String) {
        savedStateHandle[TASK_TITLE_KEY] = title
    }

    fun onClickOnSave() {
        viewModelScope.launch {
            val title = taskTitle.value
            if (title.isBlank()) return@launch
            val task = Task(title = title)
            taskRepository.addTask(task)
            //Clear out the task title to get ready for new task
            onTaskTitleChanged(title = "")
        }
    }
}

private const val TASK_TITLE_KEY = "searchQuery"
