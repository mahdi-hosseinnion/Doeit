package com.ssmmhh.doeit.tasks

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ssmmhh.doeit.QuickAdd
import com.ssmmhh.doeit.R
import com.ssmmhh.doeit.TaskDetail
import com.ssmmhh.doeit.data.Task
import com.ssmmhh.doeit.ui.theme.DoeitTheme


@Composable
fun TasksScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    tasksViewModel: TasksViewModel = viewModel(),
) {
    val todayTasks by tasksViewModel.todayTask.collectAsState()

    TasksScreen(
        todayTasks = todayTasks,
        modifier = modifier,
        addTask = { navController.navigate(route = QuickAdd) },
        onClickOnTask = { task -> navController.navigate(route = TaskDetail(task.id)) })
}

@Composable
private fun TasksScreen(
    todayTasks: List<Task>,
    modifier: Modifier = Modifier,
    addTask: () -> Unit = {},
    onClickOnTask: (Task) -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxWidth(),
        floatingActionButton = {
            FloatingActionButton(onClick = addTask) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add task")
            }
        },
        topBar = {
            Text(
                text = stringResource(R.string.today),
                modifier = Modifier
                    .padding(start = 16.dp, top = 80.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    ) { padding ->
        TaskListContent(
            tasks = todayTasks,
            modifier = Modifier.padding(padding),
            onClickOnTask = onClickOnTask
        )
    }
}

@Composable
fun TaskListContent(
    tasks: List<Task>,
    modifier: Modifier = Modifier,
    onClickOnTask: (Task) -> Unit = {}
) {
    val tasksToShow = remember(tasks) { orderByComplete(tasks) }
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(tasksToShow) {
            TaskItem(it, {}, onClick = onClickOnTask)
        }
    }
}

fun orderByComplete(todayTasks: List<Task>): List<Task> {
    val (completedTasks, remainingTasks) = todayTasks.partition { it.isCompleted }
    return remainingTasks + completedTasks
}

@Composable
fun TaskItem(
    task: Task,
    onToggleCompleted: (Boolean) -> Unit,
    onClick: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        ),
        shape = RoundedCornerShape(16.0.dp),
        modifier = modifier.clickable { onClick(task) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 4.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
                .fillMaxWidth()
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = onToggleCompleted
            )
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7,
)
@Composable
fun TaskScreenWithTaskPreview() {
    DoeitTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TasksScreen(
                todayTasks = mockTasks,
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun TaskScreenWithTaskPreviewDarkMode() {
    DoeitTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TasksScreen(
                todayTasks = mockTasks,
            )
        }
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
                {}, {}
            )
        }
    }
}

val mockTasks = listOf(
    Task(title = "Design task list screen"),
    Task(title = "Do the laundry", isCompleted = true),
    Task(title = "Learn about compose modifiers")
)