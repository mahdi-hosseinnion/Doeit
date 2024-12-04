package com.ssmmhh.doeit

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.ssmmhh.doeit.quickadd.QuickAddDialog
import com.ssmmhh.doeit.quickadd.QuickDialogDialogProperties
import com.ssmmhh.doeit.taskdetail.TaskDetailScreen
import com.ssmmhh.doeit.tasks.TasksScreen
import kotlinx.serialization.Serializable

@Serializable
object Tasks

@Serializable
data class TaskDetail(val taskId: String)

@Serializable
object QuickAdd

@Composable
fun DoeitNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Tasks) {
        composable<Tasks> { TasksScreen(navController) }
        composable<TaskDetail> { TaskDetailScreen(navController = navController) }
        dialog<QuickAdd>(dialogProperties = QuickDialogDialogProperties) {
            QuickAddDialog(
                navController = navController
            )
        }
    }
}