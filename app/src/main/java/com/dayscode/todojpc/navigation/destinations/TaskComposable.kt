package com.dayscode.todojpc.navigation.destinations

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navArgument
import com.dayscode.todojpc.data.models.ToDoTask
import com.dayscode.todojpc.data.viewmodels.SharedViewModel
import com.dayscode.todojpc.ui.screens.task.TaskScreen
import com.dayscode.todojpc.util.Action
import com.dayscode.todojpc.util.Constants.LIST_ARGUMENT_KEY
import com.dayscode.todojpc.util.Constants.LIST_SCREEN
import com.dayscode.todojpc.util.Constants.TASK_ARGUMENT_KEY
import com.dayscode.todojpc.util.Constants.TASK_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        enterTransition = {
            when(initialState.destination.route){
                LIST_SCREEN ->
                    slideInHorizontally(
                        animationSpec = tween(
                            durationMillis = 600
                        ),
                        initialOffsetX = {it}
                    )
                else -> null
            }
        },
        exitTransition = {
            when(initialState.destination.route){
                TASK_SCREEN->
                    slideOutHorizontally(
                        animationSpec = tween(
                            durationMillis = 600
                        ),
                        targetOffsetX = {-it}

                    )

                else -> null
            }
        },
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })


    ) { navBackStackEntry ->

        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)


        LaunchedEffect(key1 = taskId){

            sharedViewModel.getSelectedTask(taskId)
        }

        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask){
            if (selectedTask != null || taskId == -1){
                sharedViewModel.updateTaskFields(selectedTask)
            }

        }
        TaskScreen(
            navigateToListScreen = navigateToListScreen,
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel
        )

    }

}