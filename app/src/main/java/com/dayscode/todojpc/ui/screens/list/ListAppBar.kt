package com.dayscode.todojpc.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.dayscode.todojpc.components.PriorityItem
import com.dayscode.todojpc.data.models.Priority
import com.dayscode.todojpc.ui.theme.LARGE_PADDING
import com.dayscode.todojpc.ui.theme.Typography
import com.dayscode.todojpc.ui.theme.topAppBarBackgroundColor
import com.dayscode.todojpc.ui.theme.topAppBarContentColor
import kotlin.math.exp

@Composable
fun ListAppBar(

) {

    DefaultListAppBar(onSearchClicked = { }, onSortClicked = { }, onDeleteClicked = {})
}

@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(title = {
        Text(text = "Tasks", color = MaterialTheme.colors.topAppBarContentColor)
    }, actions = {

        ListAppBarActions(
            onSearchClicked = onSearchClicked,
            onSortClicked = onSortClicked,
            onDeleteClicked = onDeleteClicked
        )

    }, backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {

    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {

    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Tasks",
            tint = MaterialTheme.colors.topAppBarContentColor
        )

    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false) //by default the drop down menu will not be in the expanded state
    }
    IconButton(
        onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = com.dayscode.todojpc.R.drawable.ic_filter_list),
            contentDescription = "Sort Tasks",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = {
                onSortClicked(Priority.LOW)
                expanded = false
            }) {
                PriorityItem(priority = Priority.LOW)
            }
            DropdownMenuItem(onClick = {
                onSortClicked(Priority.HIGH)
                expanded = false
            }) {
                PriorityItem(priority = Priority.HIGH)
            }
            DropdownMenuItem(onClick = {
                onSortClicked(Priority.NONE)
                expanded = false
            }) {
                PriorityItem(priority = Priority.NONE)

            }
        }
    }

}

@Composable
fun DeleteAllAction(
    onDeleteClicked: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true }) {
        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Delete All Tasks",
        tint = MaterialTheme.colors.topAppBarContentColor)
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = {
                expanded = false
                onDeleteClicked()
            }) {
                Text(
                    modifier = Modifier.padding(LARGE_PADDING), text = "Delete All",
                    style = Typography.subtitle2
                )

            }
        }
    }

}

@Composable
@Preview
private fun DefaultAppBarPreview() {
    DefaultListAppBar(onSearchClicked = {}, onSortClicked = {}, onDeleteClicked = {})
}