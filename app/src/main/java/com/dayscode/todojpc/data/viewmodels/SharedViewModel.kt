package com.dayscode.todojpc.data.viewmodels

import android.icu.text.Normalizer.NO
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.dayscode.todojpc.data.models.Priority
import com.dayscode.todojpc.data.models.ToDoTask
import com.dayscode.todojpc.data.repositories.ToDoRepository
import com.dayscode.todojpc.util.Action
import com.dayscode.todojpc.util.Constants.MAX_TITLE_LENGTH
import com.dayscode.todojpc.util.RequestState
import com.dayscode.todojpc.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: ToDoRepository) :
    ViewModel() {

    val action: MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val id: MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    //Whenever we open up the list screen search app bar will be closed by defaults
    val searchAppState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)

    //This one triggered when the text get changed inside the search text field
    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)

    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTasks

    private val _searchedTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)

    val searchedTasks: StateFlow<RequestState<List<ToDoTask>>> = _searchedTasks

    fun searchDatabase(searchQuery: String){
        _searchedTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.searchDatabase(searchQuery = "%$searchQuery%")
                    .collect{ searchedTasks->

                        _searchedTasks.value = RequestState.Success(searchedTasks)

                    }
            }
        }catch (e: Exception){
            _searchedTasks.value = RequestState.Error(e)
        }
        searchAppState.value = SearchAppBarState.TRIGGERED
    }

    fun getAllTasks() {
        _allTasks.value = RequestState.Loading

        try {

            viewModelScope.launch {
                repository.getAllTasks.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }

        } catch (e: Exception) {
            _allTasks.value = RequestState.Error(e)
        }

    }

    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            repository.addTask(toDoTask)
        }
        searchAppState.value = SearchAppBarState.CLOSED
    }

    private fun updateTask(){
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )

            repository.updateTask(toDoTask)
        }
    }

    private fun deleteTask(){
        viewModelScope.launch(Dispatchers.IO) {
            val toDoTask = ToDoTask(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )

            repository.deleteTask(toDoTask)
        }
    }

    private fun deleteAllTasks(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTasks()
        }
    }

    fun handleDatabaseActions(action: Action){
        when (action){
            Action.ADD ->{
                addTask()
            }
            Action.UPDATE ->{
                updateTask()
            }
            Action.DELETE ->{
                deleteTask()
            }
            Action.DELETE_ALL ->{
                deleteAllTasks()
            }
            Action.UNDO ->{

                addTask()

            }
            else -> {

            }
        }
        this.action.value = Action.NO_ACTION
    }

    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask


    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            repository.getSelectedTask(taskId = taskId).collect { task ->

                _selectedTask.value = task

            }
        }

    }

    fun updateTaskFields(selectedTask: ToDoTask?) {
        if (selectedTask != null) {
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    fun updateTitle(newTitle: String) {
        if (newTitle.length < MAX_TITLE_LENGTH) {
            title.value = newTitle
        }
    }

    fun validateFields(): Boolean {
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }

}