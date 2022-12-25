package com.dayscode.todojpc.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dayscode.todojpc.util.Action
import com.dayscode.todojpc.util.Constants.LIST_ARGUMENT_KEY
import com.dayscode.todojpc.util.Constants.LIST_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.IntType
        })


    ){

    }

}