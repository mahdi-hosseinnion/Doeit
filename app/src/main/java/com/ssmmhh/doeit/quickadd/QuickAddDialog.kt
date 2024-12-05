package com.ssmmhh.doeit.quickadd

import android.view.Gravity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ssmmhh.doeit.OpenKeyboard
import com.ssmmhh.doeit.ui.theme.DoeitTheme

@Composable
fun QuickAddDialog(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: QuickAddViewModel = viewModel()
) {

    val taskTitle by viewModel.taskTitle.collectAsStateWithLifecycle()

    QuickAddDialog(
        modifier = modifier,
        taskTitle = taskTitle,
        onTaskTitleChanged = viewModel::onTaskTitleChanged,
        onClickOnSave = viewModel::onClickOnSave,
    )
}

@Composable
private fun QuickAddDialog(
    modifier: Modifier = Modifier,
    taskTitle: String = "",
    onTaskTitleChanged: (String) -> Unit = {},
    onClickOnSave: () -> Unit = {}
) {
    SetDialogGravityToBottom()
    Surface(
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row {
            val focusRequester = remember { FocusRequester() }
            TextField(
                value = taskTitle,
                onValueChange = onTaskTitleChanged,
                placeholder = { Text("Add a task") },
                modifier = Modifier.focusRequester(focusRequester)
            )
            OpenKeyboard(focusRequester)
            Button(
                onClick = onClickOnSave,
                enabled = taskTitle.isNotBlank()
            ) {
                Icon(Icons.AutoMirrored.Rounded.Send, contentDescription = "Add the task")
            }
        }
    }

}

@Composable
private fun SetDialogGravityToBottom(modifier: Modifier = Modifier) {
    val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
    dialogWindowProvider.window.setGravity(Gravity.BOTTOM)
}

@Preview
@Composable
private fun QuickAddDialogPreview() {
    DoeitTheme {
        QuickAddDialog()
    }
}

val QuickDialogDialogProperties = DialogProperties(
    usePlatformDefaultWidth = false
)
