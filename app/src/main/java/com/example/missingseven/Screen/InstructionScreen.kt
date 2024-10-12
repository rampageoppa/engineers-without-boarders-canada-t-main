package com.example.missingseven.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.core.content.res.ResourcesCompat.ID_NULL
import com.example.missingseven.Database.Entity.getImageRes
import com.example.missingseven.R
import com.example.missingseven.ViewModel.FilterViewModel

/***
 * composable function for instruction screen
 */
@Composable
fun InstructionScreen(
    viewModel: FilterViewModel
){
    var popupControl by remember {
        mutableStateOf(false)
    }
    val imageRes = viewModel.getPlayerCountry()?.getImageRes().takeIf { it != ID_NULL }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Instructions:", textAlign = TextAlign.Center, fontSize = 30.sp)
        Text(text = viewModel.getInstruction(),
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 10.dp),
            fontSize = 20.sp)
        viewModel.getPlayerCountry()?.let {
            if (it.hasDifficultyReading){
                Text(
                    text = stringResource(id = R.string.difficulty_reading),
                    textDecoration = TextDecoration.Underline,
                    color = Color.Red,
                    modifier = Modifier.padding(10.dp),
                    fontSize = 20.sp
                )
            }
        }
        imageRes?.let {
            Button(onClick = { popupControl = true }) {
                Text(text = "See filter image")
            }
        }
        Button(onClick = { viewModel.navigateBack() }) {
            Text(text = "Close")
        }
    }
    imageRes?.let {
        if (popupControl){
            Popup(
                onDismissRequest = { popupControl = false },
                alignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "filter image",
                    modifier = Modifier.size(300.dp)
                )
            }
        }
    }
}