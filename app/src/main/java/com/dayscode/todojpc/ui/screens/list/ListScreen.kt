package com.dayscode.todojpc.ui.screens.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dayscode.todojpc.data.viewmodels.SharedViewModel
import com.dayscode.todojpc.util.Action
import com.dayscode.todojpc.util.SearchAppBarState
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {

    LaunchedEffect(key1 = true) {
        Log.d("ListScreen", "Launched Effect Triggered")
        sharedViewModel.getAllTasks()
    }

    val action by sharedViewModel.action

    //collect as state function collect the value as the state flow and it will update the all tasks variable when there is a change in the database
    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppState
    val searchTextState: String by sharedViewModel.searchTextState

    val sortState by sharedViewModel.sortState.collectAsState()

    val lowPriorityTasks by sharedViewModel.lowPriorityTasks.collectAsState()

    val highPriorityTasks by sharedViewModel.highPriorityTasks.collectAsState()

    val scaffoldState = rememberScaffoldState()

    sharedViewModel.readSortState()

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseActions = { sharedViewModel.handleDatabaseActions(action) },
        taskTitle = sharedViewModel.title.value,
        action = action,
        onUndoClicked = {
            sharedViewModel.action.value = it
        }
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {

            ListContent(
                allTasks = allTasks, navigateToTaskScreen = navigateToTaskScreen,
                searchedTasks = searchedTasks, searchAppBarState = searchAppBarState,
                sortState = sortState,
                lowPriorityTasks = lowPriorityTasks,
                highPriorityTasks = highPriorityTasks,
                onSwipeToDelete = {action, task->
                    sharedViewModel.action.value = action
                    sharedViewModel.updateTaskFields(selectedTask = task)
                }
            )
        },
        floatingActionButton = {
            ListFab(OnFabClicked = navigateToTaskScreen)
        })

}

@Composable
fun ListFab(
    OnFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(onClick = {
        OnFabClicked(-1)
    }, backgroundColor = MaterialTheme.colors.primary) {

        Icon(
            imageVector = Icons.Filled.Add, contentDescription = "Add Button",
            tint = Color.White
        )

    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseActions: () -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {

    handleDatabaseActions()

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = setMessage(action, taskTitle),
                    actionLabel = setActionLabel(action)
                )
                undoDeletedTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
    }

}

fun setMessage(action: Action, taskTitle: String): String{
    return when(action){
        Action.DELETE_ALL -> "All tasks removed..."
        else -> "${action.name}: $taskTitle"
    }

}

private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(Action.UNDO)
    }
}
