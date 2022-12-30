package com.dayscode.todojpc.ui.screens.task

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.dayscode.todojpc.data.models.Priority
import com.dayscode.todojpc.data.models.ToDoTask
import com.dayscode.todojpc.ui.theme.topAppBarBackgroundColor
import com.dayscode.todojpc.ui.theme.topAppBarContentColor
import com.dayscode.todojpc.util.Action

@Composable
fun TaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {

    NewTaskAppBar(navigateToListScreen = navigateToListScreen)

}

@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {

            BackAction(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = "Add Task",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {

            AddAction(onAddClicked = navigateToListScreen)

        }
    )
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Button",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Add Task",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun ExistingTaskAppBar(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(navigateToListScreen = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colors.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            DeleteAction(navigateToListScreen = navigateToListScreen)
            UpdateAction(navigateToListScreen = navigateToListScreen)

        }
    )
}

@Composable
fun CloseAction(
    navigateToListScreen: (Action) -> Unit
) {
    IconButton(onClick = { navigateToListScreen(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Close",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAction(
    navigateToListScreen: (Action) -> Unit
) {
    IconButton(onClick = { navigateToListScreen(Action.DELETE) }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete Task",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateAction(
    navigateToListScreen: (Action) -> Unit
) {
    IconButton(onClick = { navigateToListScreen(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Update Task",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
@Preview
fun TopAppBarPreview() {
    NewTaskAppBar(navigateToListScreen = {})
}

@Composable
@Preview
fun ExistingTaskPreview() {
    ExistingTaskAppBar(
        selectedTask = ToDoTask(
            0,
            "aldsjkflakjdf;lkadjslk",
            "lsadjflakjsjd",
            Priority.HIGH
        ), navigateToListScreen = {})
}