package com.example.missingseven.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.missingseven.ViewModel.TaskViewModel

/***
 * composable function for login screen
 */
@Composable
fun LoginScreen(
    viewModel: TaskViewModel
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var email by remember {
            mutableStateOf("")
        }
        var name by remember {
            mutableStateOf("")
        }
        Text(text = "Login", fontWeight = FontWeight.Bold)
        Text(text = "Please login by completing the fields below")

        OutlinedTextField(
            value = email,
            label = { Text(text = "Your instructors email address:") },
            onValueChange = {
                email = it
            }
        )

        OutlinedTextField(
            value = name,
            label = { Text(text = "Your name:") },
            onValueChange = {
                name = it
            }
        )
        
        Button(onClick = { viewModel.loginHandler(email, name) }) {
         Text(text = "Save")
        }

        Button(onClick = { viewModel.navigateBack() }) {
            Text(text = "Back")
        }
        
    }

}