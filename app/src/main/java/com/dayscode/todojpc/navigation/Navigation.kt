package com.dayscode.todojpc.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.dayscode.todojpc.data.viewmodels.SharedViewModel
import com.dayscode.todojpc.navigation.destinations.listComposable
import com.dayscode.todojpc.navigation.destinations.taskComposable
import com.dayscode.todojpc.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(navigateToTaskScreen = screen.task, sharedViewModel = sharedViewModel)
        taskComposable(navigateToListScreen = screen.list, sharedViewModel = sharedViewModel)
    }
}