package com.dayscode.todojpc.navigation

import androidx.navigation.NavHostController
import com.dayscode.todojpc.util.Action
import com.dayscode.todojpc.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {
    val list: (Action) -> Unit = {action->
        navController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN){
                inclusive = true //Whenever we come to the this screen the other screen will be pop from the backstack
            }
        }
    }

    val task: (Int) -> Unit = {taskId->
        navController.navigate("task/$taskId")

    }

}