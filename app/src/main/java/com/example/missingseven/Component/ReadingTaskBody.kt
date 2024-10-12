package com.example.missingseven.Component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.missingseven.Model.TaskUiState
import com.example.missingseven.Model.skipAble


/***
 * composable function for the reading screen
 */
@Composable
fun ReadingTaskBody(
    task: TaskUiState.ReadingTask,
    skipHandler: () -> Unit = {}
){
    if (!task.isSpecial) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = task.content,
                style = MaterialTheme.typography.h6
            )
            if (task.skipAble()){
                Button(onClick = skipHandler) {
                    Text(text = "Skip")
                }
            }
        }
    }
    else{
        FilterBuildingIntroBody(
            task
        )
    }
}