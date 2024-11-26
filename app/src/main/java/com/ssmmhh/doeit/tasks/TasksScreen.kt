package com.ssmmhh.doeit.tasks

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ssmmhh.doeit.data.Task


@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    tasksViewModel: TasksViewModel = viewModel(),
) {
    val todayTasks by tasksViewModel.todayTask.collectAsState()
    TaskScreen(todayTasks, modifier)
}

@Composable
fun TaskScreen(
    todayTasks: List<Task>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(todayTasks) {
            TaskItem(it)
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier
) {
    Text(task.title)
}
