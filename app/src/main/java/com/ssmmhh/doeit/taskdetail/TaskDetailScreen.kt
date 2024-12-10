package com.ssmmhh.doeit.taskdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
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
        TaskDetailScreen(task = it, modifier = modifier, navigateBack = navController::navigateUp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskDetailScreen(
    task: Task,
    modifier: Modifier = Modifier,
    onClickOnSave: () -> Unit = {},
    navigateBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                },
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack
                    ) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "navigate back",
                        )
                    }
                })
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
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.surface,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = { Text("Task title") },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun TaskNoteSection(
    task: Task,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.extraSmall,
        modifier = modifier
            .padding(8.dp)
            .padding(top = 8.dp)
    ) {
        TextField(
            value = task.title,
            onValueChange = {},
            placeholder = { Text("Add a note") },
            minLines = 4,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                focusedIndicatorColor = MaterialTheme.colorScheme.surfaceContainerLow,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceContainerLow
            ),
        )
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
            TaskDetailScreen(
                task = mockTasks.first(),
            )
        }
    }
}