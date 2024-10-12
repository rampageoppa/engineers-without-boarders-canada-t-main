package com.example.missingseven.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.missingseven.Component.DragAbleItem
import com.example.missingseven.Component.DropTarget
import com.example.missingseven.Component.LongPressDraggable
import com.example.missingseven.Component.WaterFilter
import com.example.missingseven.Model.TaskUiState
import com.example.missingseven.Model.getMultipliedPrice
import com.example.missingseven.Navigation.NavControl
import com.example.missingseven.ViewModel.FilterViewModel
import com.example.missingseven.R


/***
 * composable function for water filter exercise screen
 */
@Composable
fun WaterFilterScreen(
    task: TaskUiState.FilterTask,
    filterViewModel: FilterViewModel,
    navControl: NavControl
) {
    LaunchedEffect(Unit){
        filterViewModel.setup(navControl, task)
    }

    if (filterViewModel.fetchCompleted()){
        if (filterViewModel.player.cid == -1){
            val pair = filterViewModel.getParallelCountryList()
            CountryScreen(
                listA = pair.first,
                listB = pair.second,
            countryClick = {country -> filterViewModel.countrySelect(country) }){
                filterViewModel.navigateBack()
            }
        } else {
            if (!filterViewModel.setupCompleted.value){
                filterViewModel.setupPlayerUiState()
                filterViewModel.setupStack()
            } else {
                FilterMainBody(filterViewModel = filterViewModel)
            }
        }
    }
}

@Composable
fun FilterMainBody(
    filterViewModel: FilterViewModel
){
        LongPressDraggable(
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${filterViewModel.getPlayerCountry()?.name.orEmpty()} - Build filter",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                filterViewModel.getPlayerCountry()?.let {
                    if (it.multiplierHint.isNotEmpty()){
                        Text(
                            text = it.multiplierHint,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                }
                DropTarget() { isInBound, data ->
                    if (isInBound){
                        data?.let {
                            filterViewModel.addItem(it)
                        }
                    }
                    WaterFilter(stack = filterViewModel.filterStack)
                }
                Text(text = "You have $${filterViewModel.playerUiState.currMoney.value} remaining to spend on materials"
                    , modifier = Modifier.padding(vertical = 10.dp), fontSize = 15.sp)
                Text(
                    text = "Scroll left and right to see more materials",
                    fontSize = 15.sp
                )
                LazyRow {
                    items(items = filterViewModel.items
                    ){
                        DragAbleItem(dataToDrop = it) {
                            Card(
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(10.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    Text(text = it.name)
                                    Text(text = "$${it.getMultipliedPrice(
                                        filterViewModel.getPriceMultiplier())}")
                                }
                            }
                        }
                    } 
                }
                Text(text = stringResource(id = R.string.filter_hint))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(onClick = filterViewModel::openInstruction) {
                        Text(text = stringResource(id = R.string.see_instructions))
                    }
                    Button(onClick = filterViewModel::onUndoClick) {
                        Text(text = stringResource(id = R.string.undo))
                    }
                    Button(onClick = filterViewModel::onTestClicked) {
                        Text(text = stringResource(id = R.string.test))
                    }
                }
            }
        }
}