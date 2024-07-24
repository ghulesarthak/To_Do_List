package com.example.todoapp

import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.db.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class TodoViewModel : ViewModel() {

    val todoDao = MainApplication.todoDatabase.getTodoDao()
    val _tudoList: LiveData<List<Todo>> = todoDao.getAllTodo()


    fun addTodo(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodo(Todo(title = title, createdAt = Date.from(Instant.now())))
        }
    }



    fun deleteTodo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(id)

        }
    }

    fun updateTodo(id: Int, newTitle: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val todo = todoDao.getTodoById(id)
            if (todo != null) {
                todo.title = newTitle
                todoDao.updateTodo(todo)
            }
        }

    }


}