package com.dayscode.todojpc.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.dayscode.todojpc.navigation.destinations.listComposable
import com.dayscode.todojpc.navigation.destinations.taskComposable
import com.dayscode.todojpc.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(navController: NavHostController) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        taskComposable(navigateToListScreen = screen.list)
        listComposable(navigateToTaskScreen = screen.task)
    }
}