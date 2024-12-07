package com.ssmmhh.doeit

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
        val animationDuration = 300
        composable<Tasks>(
            enterTransition = {
                fadeIn(animationSpec = tween(animationDuration, easing = LinearEasing))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(animationDuration, easing = LinearEasing))
            }

        ) { TasksScreen(navController) }
        composable<TaskDetail>(
            enterTransition = {
                fadeIn(
                    animationSpec = tween(animationDuration, easing = LinearEasing)
                ) + slideIntoContainer(
                    animationSpec = tween(animationDuration, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(animationDuration, easing = LinearEasing)
                ) + slideOutOfContainer(
                    animationSpec = tween(animationDuration, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) { TaskDetailScreen(navController = navController) }
        dialog<QuickAdd>(dialogProperties = QuickDialogDialogProperties) {
            QuickAddDialog(
                navController = navController
            )
        }
    }
}