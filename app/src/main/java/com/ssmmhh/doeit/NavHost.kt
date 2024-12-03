package com.ssmmhh.doeit

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ssmmhh.doeit.data.TaskId
import com.ssmmhh.doeit.taskdetail.TaskDetailScreen
import com.ssmmhh.doeit.tasks.TasksScreen
import kotlinx.serialization.Serializable

@Serializable
object Tasks

@Serializable
data class TaskDetail(val taskId: TaskId)

@Composable
fun DoeitNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Tasks) {
        composable<Tasks> { TasksScreen(navController) }
        composable<TaskDetail> { backstack: NavBackStackEntry ->
            val taskId = backstack.toRoute<TaskDetail>().taskId
            TaskDetailScreen(navController = navController, taskId = taskId)
        }
    }
}