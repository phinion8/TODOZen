package com.dayscode.todojpc.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dayscode.todojpc.components.PriorityDropDown
import com.dayscode.todojpc.data.models.Priority
import com.dayscode.todojpc.ui.theme.LARGE_PADDING
import com.dayscode.todojpc.ui.theme.MEDIUM_PADDING

@Composable
fun TaskContent(
    title: String,
    onTitleChanged: (String) -> Unit,
    description: String,
    onDescriptionChanged: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(all = LARGE_PADDING)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title, onValueChange = { onTitleChanged(it) },
            label = { Text(text = "Title") },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true
        )
        Divider(
            modifier = Modifier.height(MEDIUM_PADDING),
            color = MaterialTheme.colors.background
        )
        PriorityDropDown(priority = priority, onPrioritySelected = onPrioritySelected)

        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description, onValueChange = { onDescriptionChanged(it) },
            label = { Text(text = "Description") }, textStyle = MaterialTheme.typography.body1
        )
    }
}

@Preview
@Composable
fun TaskContentPreview() {
    TaskContent(
        title = "",
        onTitleChanged = {},
        description = "",
        onDescriptionChanged = {},
        priority = Priority.LOW,
        onPrioritySelected = {}
    )
}