package com.example.missingseven.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyScopeMarker
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.missingseven.Model.TaskUiState
import com.example.missingseven.R

/***
 * composable function for the literacy rate screen
 */
@Composable
@LazyScopeMarker
fun LiteracyRateTaskBody(
    task: TaskUiState.LiteracyRateTask
) {
    var selectedCountry by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .border(
                0.dp,
                brush = SolidColor(Color.Black),
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "Explore the literacy rates of the highlighted countries: (click on the country name buttons to see the literacy rate)",
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(R.drawable.lrm),
                contentDescription = "Literacy Rate Map",
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = {selectedCountry = "Canada"},
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Text(text = "Canada")
                }
                Button(
                    onClick = {selectedCountry = "Germany"},
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Text(text = "Germany")
                }
                Button(
                    onClick = {selectedCountry = "Ghana"},
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Text(text = "Ghana")
                }
            }
            Row() {
                Button(
                    onClick = {selectedCountry = "Kuwait"},
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Text(text = "Kuwait")
                }
                Button(
                    onClick = {selectedCountry = "Malawi"},
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Text(text = "Malawi")
                }
                Button(
                    onClick = {selectedCountry = "Kenya"},
                ) {
                    Text(text = "Kenya")
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {selectedCountry = "South Africa"},
                ) {
                    Text(text = "South Africa")
                }
            }
        }
        if (selectedCountry.isNotEmpty()){
            Popup(
                onDismissRequest = { selectedCountry = "" },
                alignment = Alignment.Center
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
                ) {
                    val text = when(selectedCountry){
                        "Canada" -> "Literacy rate of Canada is: " + task.CanadaRate + "%"
                        "Germany" -> "Literacy rate of Germany is: " + task.GermanyRate + "%"
                        "Ghana" -> "Literacy rate of Ghana is: " + task.GhanaRate + "%"
                        "Kenya" -> "Literacy rate of Kenya is: " + task.KenyaRate + "%"
                        "Kuwait" -> "Literacy rate of Kuwait is: " + task.KuwaitRate + "%"
                        "Malawi" -> "Literacy rate of Malawi is: " + task.MalawiRate + "%"
                        "South Africa" -> "Literacy rate of South Africa is: " +
                                task.SouthAfricaRate + "%"
                        else -> ""
                    }
                    Text(
                        text = text,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
