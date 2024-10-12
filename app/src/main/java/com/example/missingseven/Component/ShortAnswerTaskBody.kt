package com.example.missingseven.Component

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.missingseven.Model.TaskUiState

/***
 * composable function for the short answer screen
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShortAnswerTaskBody(
    saveHandler: () -> Unit,
    task: TaskUiState.ShortAnswerTask,
    answerValueHandler: (String) -> Unit,
    submitHandler: (Context) -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 10.dp)
    ) {

        val keyboardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(
            value = task.answer.value,
            label = { Text(text = "Enter Your Answer") },
            onValueChange = {
                answerValueHandler(it)
            },
            modifier = Modifier
                .height(250.dp)
                .padding(horizontal = 5.dp, vertical = 20.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    saveHandler()
                    keyboardController?.hide()
                }
            )
        )

        Button(
            modifier =  Modifier.padding(10.dp),
            shape = RoundedCornerShape(50),
            onClick = {
                saveHandler()
                keyboardController?.hide()
            }
        ) {
            val text = if (task.completed.value) "Saved" else "Save Answer"
            Text(text = text)
        }
        if (task.isLast){
            val context = LocalContext.current
            Button(onClick = { submitHandler(context) }) {
                Text(text = "Submit Your Answer to your teacher!")
            }
        }
    }
}