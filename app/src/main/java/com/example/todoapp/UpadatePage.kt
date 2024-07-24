/*package com.example.todoapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UpdatePage(viewModel: TodoViewModel) {

    val todoList by viewModel.todoList.observeAsState(emptyList()) // Corrected variable name and provided a default value
    var inputText by remember {
        mutableStateOf("")
    }

    fun update(editText: String) {
        // Implement the update logic here
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(10.dp, 50.dp, 0.dp, 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = {
                    inputText = it
                }
            )

            Button(onClick = {
                // Assuming 'item' is defined somewhere in your code
                viewModel.updateTodo(item.id, inputText)
                inputText = ""
            }) {
                Text(text = "Edit")
            }
        }
    }
}
*/