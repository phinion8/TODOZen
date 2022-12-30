package com.dayscode.todojpc.navigation.destinations

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dayscode.todojpc.ui.screens.task.TaskScreen
import com.dayscode.todojpc.util.Action
import com.dayscode.todojpc.util.Constants.LIST_ARGUMENT_KEY
import com.dayscode.todojpc.util.Constants.LIST_SCREEN
import com.dayscode.todojpc.util.Constants.TASK_ARGUMENT_KEY
import com.dayscode.todojpc.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })


    ){navBackStackEntry ->

    val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

    Log.d("Task", taskId.toString())
        
        TaskScreen(navigateToListScreen = navigateToListScreen)

    }

}