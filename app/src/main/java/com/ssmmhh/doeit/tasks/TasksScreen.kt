package com.ssmmhh.doeit.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ssmmhh.doeit.R
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
private fun TaskScreen(
    todayTasks: List<Task>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.today),
            modifier = Modifier.padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineMedium,
        )
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            items(todayTasks) {
                TaskItem(it, {})
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onToggleCompleted: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.0.dp),
        modifier = modifier.background(MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 4.dp, end = 16.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = onToggleCompleted
            )
            Text(
                text = task.title,
                style = MaterialTheme.typography.labelLarge,
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_7)
@Composable
fun TaskScreenWithTaskPreview() {
    DoeitTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            TaskScreen(
                todayTasks = mockTasks,
                modifier = Modifier.padding(innerPadding)
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
                {}
            )
        }
    }
}

val mockTasks = listOf(
    Task(title = "Design task list screen"),
    Task(title = "Learn about compose modifiers"),
    Task(title = "Do the laundry", true)
)