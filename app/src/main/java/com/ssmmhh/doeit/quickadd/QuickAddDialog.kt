package com.ssmmhh.doeit.quickadd

import android.view.Gravity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
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
    QuickAddDialog()
}

@Composable
private fun QuickAddDialog(
    modifier: Modifier = Modifier
) {
    val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
    dialogWindowProvider.window.setGravity(Gravity.BOTTOM)
    Surface(
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        val focusRequester = remember { FocusRequester() }
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Add a task") },
            modifier = Modifier.focusRequester(focusRequester)
        )
        OpenKeyboard(focusRequester)

    }

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
