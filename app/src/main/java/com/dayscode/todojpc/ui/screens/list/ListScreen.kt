package com.dayscode.todojpc.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dayscode.todojpc.data.viewmodels.SharedViewModel
import com.dayscode.todojpc.util.SearchAppBarState


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppState
    val searchTextState: String by sharedViewModel.searchTextState

    Scaffold(
        topBar = {
            ListAppBar(sharedViewModel= sharedViewModel, searchAppBarState = searchAppBarState, searchTextState = searchTextState)
        },
        content = {

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
