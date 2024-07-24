package com.example.todoapp


import android.content.ClipData.Item
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.AlertDialog

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Update
import java.text.SimpleDateFormat
import java.util.Locale




@Composable
fun TodoListPage(viewModel: TodoViewModel){
 val todoList by viewModel._tudoList.observeAsState()
    var inputText by remember{
        mutableStateOf("")
    }

    fun upadate(editText:String){

    }


    Column(
        modifier = Modifier.fillMaxHeight().padding(10.dp,50.dp,0.dp,0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)

        )
        {
            OutlinedTextField(value =inputText , onValueChange ={
                inputText= it
            })

            Button(onClick = {
                viewModel.addTodo(inputText)
                inputText=""
            }){
                Text(text = "Add")
            }

        }




        todoList?.let {
            LazyColumn (
                content = {
                    itemsIndexed(it){
                            index: Int, item: Todo ->
                       TodoItem(item=item, viewModel

                        )

                    }
                }
            )
        }?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "No items Yet",
            fontSize = 20.sp
        )


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItem (item: Todo, viewModel: TodoViewModel){
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically


    ) {














        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm:aa,dd//mm", Locale.ENGLISH).format(item.createdAt),

                fontSize = 12.sp,
                color = Color.DarkGray
            )
            Text(
                text = item.title,
                fontSize = 20.sp,
                color = Color.White
            )

        }



        val isDialog= remember{ mutableStateOf(false) }
        if (isDialog.value) {
            AlertDialog(
                onDismissRequest = { isDialog.value=false },
                title = { Text(text = "Confirm") },
                text = { Text(text = "Are you Sure to Delete",
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )},
                confirmButton = {
                    Button(
                        onClick = { viewModel.deleteTodo(item.id) },
                        colors = ButtonDefaults.buttonColors(Color.Cyan)

                    ) {
                        Text(text = "OK", color = Color.DarkGray, fontWeight = FontWeight.Bold)
                    }
                     Button(
                         onClick = { isDialog.value = false },
                         modifier = Modifier.padding(6.dp),
                         colors = ButtonDefaults.buttonColors(Color.Black)
                     )
                     {
                         Text(text = "No", color = Color.White, fontWeight = FontWeight.Bold)
                     }
                }

            )
        }

/*
        IconButton(onClick = onUpdate,
            modifier = Modifier.padding(6.dp))
        {
            Icon(
                painter = painterResource(id = R.drawable.baseline_done_24),
                contentDescription = "Edit",
                tint = Color.White

            )

        }*/

        var showDialog by remember { mutableStateOf(false) }
        TextButton(onClick = { showDialog = true }, modifier = Modifier.padding(6.dp)) {
            Text(text = "Edit", color = Color.White)
        }

        // Confirmation Dialog

        var editText by remember{
            mutableStateOf("")
        }



        if (showDialog) {

            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Confirm Edit") },
               // text = { Text("Are you sure you want to Edit this todo item?") },
                text={ OutlinedTextField(value =editText , onValueChange ={
                    editText= it
                })},
                confirmButton = {
                    Button(onClick = {
                        showDialog = false
                        viewModel.updateTodo(item.id,editText)

                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }





        IconButton(onClick = {isDialog.value=true},
         modifier = Modifier.padding(6.dp))
        {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_outline_24),
                contentDescription = "Delete",
                tint = Color.White

            )

        }

    }
}
