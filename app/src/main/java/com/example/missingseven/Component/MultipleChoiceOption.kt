package com.example.missingseven.Component

import androidx.compose.foundation.layout.Row
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
/***
 * composable function for the multiple choice option
 */
@Composable
fun MultipleChoiceOption(
    index: Int,
    correctIndex: Int,
    onSelected: () -> Unit,
    content: String
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = index == correctIndex,
            onClick = onSelected)
        Text(text = content)
    }
}