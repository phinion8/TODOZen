package com.dayscode.todojpc.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.dayscode.todojpc.util.Action
import com.dayscode.todojpc.util.Constants.LIST_SCREEN
import com.dayscode.todojpc.util.Constants.SPLASH_SCREEN

class Screens(navController: NavHostController) {
    val splash: () -> Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION}"){
            popUpTo(SPLASH_SCREEN){
                inclusive = true
            }
        }
    }
    val list: (Action) -> Unit = {action->
        navController.navigate(route = "list/${action.name}"){
            popUpTo(LIST_SCREEN){
                inclusive = true //Whenever we come to the this screen the other screen will be pop from the backstack
            }
        }
    }

    val task: (Int) -> Unit = {taskId->
        navController.navigate(route = "task/$taskId")

    }

}