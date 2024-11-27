package com.ssmmhh.doeit.tasks

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ssmmhh.doeit.data.Task
import com.ssmmhh.doeit.ui.theme.DoeitTheme


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
            TaskItem(it, {})
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onToggleCompleted: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = onToggleCompleted
        )
        Text(
            task.title
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    DoeitTheme {
        Surface {
            TaskItem(
                Task(
                    title = "Wash the dishes",
                    isCompleted = true
                ),
                {}
            )
        }
    }
}