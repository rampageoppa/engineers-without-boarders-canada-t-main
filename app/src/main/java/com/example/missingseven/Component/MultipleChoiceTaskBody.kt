package com.example.missingseven.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.missingseven.Model.TaskUiState
import com.example.missingseven.R


/***
 * composable function for the multiple choice screen
 */
@Composable
@LazyScopeMarker
fun MultipleChoiceTaskBody(
    completeHandler: (Int) -> Unit = {},
    task: TaskUiState.MultipleChoiceTask
) {
    var popupControl by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        LazyColumn () {
            itemsIndexed(task.options) { index, option ->
                MultipleChoiceOption(
                    index = index,
                    correctIndex = task.studentAnswerIndex.value,
                    onSelected = {
                        completeHandler(index)
                        popupControl = true
                                 },
                    content = option
                )
            }
        }
        Image(
            painter = painterResource(id = R.mipmap.ic_knowledge_world),
            contentDescription = "img",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        if (popupControl){
            Popup(
                alignment = Alignment.Center,
                onDismissRequest = {
                    popupControl = false
                },
                properties = PopupProperties(
                    focusable = true
                )
            ) {
                Box(
                    modifier = Modifier
                        .padding(24.dp)
                        .background(Color.White)
                        .border(
                            0.dp,
                            brush = SolidColor(Color.Black),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .clickable {
                            popupControl = false
                        }
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {

                        Text(
                            text = if (task.completed.value) task.popup else "Wrong Answer",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}