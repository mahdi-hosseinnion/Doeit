package com.ssmmhh.doeit.quickadd

import android.view.Gravity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ssmmhh.doeit.OpenKeyboard
import com.ssmmhh.doeit.R
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
        contentColor = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(4.dp)) {
            val focusRequester = remember { FocusRequester() }
            TextField(
                value = taskTitle,
                onValueChange = onTaskTitleChanged,
                placeholder = { Text(stringResource(R.string.add_task)) },
                textStyle = MaterialTheme.typography.titleMedium,
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = MaterialTheme.colorScheme.surface,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.surface
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { onClickOnSave() }),
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .weight(1f)
            )
            OpenKeyboard(focusRequester)
            val isButtonEnabled = taskTitle.isNotBlank()
            Button(
                onClick = onClickOnSave,
                enabled = isButtonEnabled,
                colors = ButtonDefaults.buttonColors().copy(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(4.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)

            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.Send,
                    contentDescription = "Add a task",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }

}

@Composable
private fun SetDialogGravityToBottom() {
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
