package com.ssmmhh.doeit.taskdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ssmmhh.doeit.R
import com.ssmmhh.doeit.data.Task
import com.ssmmhh.doeit.tasks.mockTasks
import com.ssmmhh.doeit.ui.theme.DoeitTheme

@Composable
fun TaskDetailScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    taskDetailViewModel: TaskDetailViewModel = viewModel(),
) {
    val task by taskDetailViewModel.task.collectAsStateWithLifecycle()
    task?.let {
        TaskDetailScreen(task = it, modifier)
    }
}

@Composable
private fun TaskDetailScreen(
    task: Task,
    modifier: Modifier = Modifier,
    onClickOnSave: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Text(
                stringResource(R.string.task_detail) + " - " + task.id.take(10),
                modifier = Modifier.padding(top = 80.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClickOnSave) {
                Icon(imageVector = Icons.Rounded.Done, "save task")
            }
        },
        modifier = modifier
    ) { innerPadding ->
        TaskDetailContent(task, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun TaskDetailContent(
    task: Task,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        TaskTitleSection(task)
        TaskNoteSection(task)
    }
}

@Composable
fun TaskTitleSection(
    task: Task,
    modifier: Modifier = Modifier
) {
    TextField(
        value = task.title,
        onValueChange = {},
        placeholder = { Text("Task title") },
        modifier = modifier
    )
}

@Composable
fun TaskNoteSection(
    task: Task,
    modifier: Modifier = Modifier
) {
    TextField(
        value = task.title,
        onValueChange = {},
        placeholder = { Text("note") },
        minLines = 5,
        modifier = modifier
    )
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
            TaskDetailScreen(
                task = mockTasks.first(),
            )
        }
    }
}