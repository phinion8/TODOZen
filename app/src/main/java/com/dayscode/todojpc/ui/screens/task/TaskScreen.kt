package com.dayscode.todojpc.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun TaskScreen(
    navigateToListScreen: (com.dayscode.todojpc.util.Action) -> Unit
) {
    Scaffold(
        topBar = { TaskAppBar(navigateToListScreen = navigateToListScreen)},
        content = {}
    )
}