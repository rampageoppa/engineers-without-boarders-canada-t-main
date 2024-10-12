package com.example.missingseven.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.missingseven.R
import com.example.missingseven.ViewModel.FilterViewModel
import kotlin.math.round


/***
 * composable function for filter test screen
 */
@Composable
fun TestScreen(
    viewModel: FilterViewModel,
    exitClick: () -> Unit
) {
    val score = round(viewModel.filterStack.evaluation() * 100 / 100)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {
        Text(
            text = "FILTER TEST STATION",
            style = MaterialTheme.typography.h5,
            textDecoration = TextDecoration.Underline
        )
        viewModel.getPlayerCountry()?.let { 
            Text(
                text = it.name,
                style = MaterialTheme.typography.h5,
                textDecoration = TextDecoration.Underline
            )
        }
        Image(painter = painterResource(
            id = R.drawable.filter_test),
            contentDescription = "filter test",
            Modifier.size(300.dp)
        )
        Text(
            text = "Your dirty water is now ${score}% cleaned",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = 5.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = if (score < 95) {
                "DO NOT DRINK - you may get sick and die!!"
            } else {
                "CONGRATULATIONS - you can drink this water - it is clean enough to drink"
            },
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(5.dp),
            textAlign = TextAlign.Center
        )

        Button(onClick = viewModel::navigateBack) {
            Text(text = "BACK to filter build")
        }
        Button(onClick = viewModel::tryAnotherCountry) {
           Text(text = "TRY ANOTHER COUNTRY")
        }
        Button(onClick = exitClick) {
            Text(text = "EXIT filter building")
        }
    }
}