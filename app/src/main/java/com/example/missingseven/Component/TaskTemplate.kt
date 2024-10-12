package com.example.missingseven.Component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.missingseven.Model.TaskUiState
import com.example.missingseven.R


/***
 * composable function that is the bask template for all the tasks
 */
@Composable
fun TaskTemplate(
    content: @Composable () -> Unit = { Surface {
        Text(text = "Hello")
    }
    },
    taskUiState: TaskUiState,
    backHandler: () -> Unit,
    nextHandler: () -> Unit,
    finishHandler: () -> Unit,
    shouldShowFirst: Boolean,
    shouldShowLast: Boolean
){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(10.dp)) {

        if (taskUiState is TaskUiState.FilterTask){
            content()
            Spacer(modifier = Modifier.weight(1f))

            Row {
                if (shouldShowFirst){
                    Button(onClick =  backHandler ) {
                        Text(text = "Back")
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                if (shouldShowLast){
                    Button(onClick = nextHandler,
                        colors = ButtonDefaults.buttonColors(backgroundColor = if (taskUiState.completed.value) Color.Green else Color.Gray)) {
                        Text(text = "Next")
                    }
                }
            }
        } else {
            HeaderView(
                header = taskUiState.header,
                subtitle = taskUiState.subtitle
            )
            content()
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.ewb),
                contentDescription = "engineers without borders",
                modifier = Modifier.size(100.dp)
            )
            Row {
                if (shouldShowFirst){
                    Button(onClick =  backHandler ) {
                        Text(text = "Back")
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                val nextColor = ButtonDefaults.buttonColors(backgroundColor = if (taskUiState.completed.value) Color.Green else Color.Gray)
                if (shouldShowLast){
                    Button(onClick = nextHandler,
                        colors = nextColor) {
                        Text(text = "Next")
                    }
                } else {
                    Button(onClick = finishHandler ,
                        colors = nextColor
                    ) {
                        Text(text = "Finish")
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderView(
    header: String,
    subtitle: String
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Column() {
            Text(
                text = header,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth(0.7f),
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline
            )
            if (subtitle.isNotEmpty()){
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.fillMaxWidth(0.7f)
                        .padding(start = 5.dp, top = 5.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.w4tw),
            contentDescription = "Water For The World",
            modifier = Modifier.size(130.dp)
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun TaskTemplatePreview(){
    TaskTemplate(
        {},
        TaskUiState.ReadingTask(1, mutableStateOf(false), "ABsssaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaCafaefafaABC","", "qwertyuiop[qwertyuiopqwertyuiopqwertyuiop"),
        {},
        {},
        {},
        true, true
    )
}