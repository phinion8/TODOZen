package com.dayscode.todojpc.data.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dayscode.todojpc.data.models.ToDoTask
import com.dayscode.todojpc.data.repositories.ToDoRepository
import com.dayscode.todojpc.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: ToDoRepository) :
    ViewModel() {

    //Whenever we open up the list screen search app bar will be closed by defaults
    val searchAppState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)

    //This one triggered when the text get changed inside the search text field
    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTasks = MutableStateFlow<List<ToDoTask>>(emptyList())

    val allTasks: StateFlow<List<ToDoTask>> = _allTasks

        fun getAllTasks(){
            viewModelScope.launch {
                repository.getAllTasks.collect{
                    _allTasks.value = it
                }
            }
        }
}