package com.dayscode.todojpc.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit
) {

    Scaffold(content = {},
        topBar = {
            ListAppBar()
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
    }, backgroundColor =  MaterialTheme.colors.primary) {

        Icon(
            imageVector = Icons.Filled.Add, contentDescription = "Add Button",
            tint = Color.White
        )

    }
}

@Composable
@Preview
private fun ListScreenPreview() {
    ListScreen(navigateToTaskScreen = {})
}