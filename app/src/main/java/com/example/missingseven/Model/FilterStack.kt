package com.example.missingseven.Model

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color

/***
 * data class for the water filter
 */
class FilterStack(
    private val itemList: MutableList<ItemUiState?>,
    val topIndex: MutableState<Int>,
    val neck: MutableState<ItemUiState?>,
    val mouth: MutableState<ItemUiState?>,
    var cleaned: Boolean = false
) {
    fun isFull() = topIndex.value == MAX_LAYER

    fun evaluation(): Double {
        if ((mouth.value?.iid ?: -1) != 4){
            return 0.0
        } else if ((neck.value?.iid ?: -1) != 5){
            return (0.5/20)*100
        } else{
            var score = 1.5
            for (i in 0 until topIndex.value){
                if ((itemList[i]?.iid ?: -1) >= 4){
                    score = (score + itemList[i]?.strength!!)
                } else if ((itemList[i]?.iid ?: -1) >= 0) {
                    if (cleaned){
                        var rare = itemList[i]?.cleanedStrength
                        for (k in i+1 until topIndex.value){
                            if (rare != null) {
                                rare *= itemList[i]?.cleanedEffectiveness!![itemList[k]?.iid!!]
                            }
                        }
                        score = (score + rare!!)
                    } else{
                        var rare = itemList[i]?.strength
                        for (k in i+1 until topIndex.value){
                            if (rare != null) {
                                rare *= itemList[i]?.effectiveness!![itemList[k]?.iid!!]
                            }
                        }
                        score = (score + rare!!)
                    }
                }
                }
            // scale up by 100/ 88
            score *= (100.0 / 88.0)
            return if (score>20.0){
                100.0
            }else{
                (score/20.0)*100
            }
            }
    }
    fun add(item: ItemUiState){
        if (mouth.value == null){
            mouth.value = item
        } else if (neck.value == null){
            neck.value = item
        } else {
            itemList[topIndex.value] = item
            topIndex.value += 1
        }
    }

    fun displayedStack(): MutableList<ItemUiState?>{
        return itemList.reversed() as MutableList<ItemUiState?>
    }

    fun getBoarderColor(reversedIndex: Int): Color {
        val index = MAX_LAYER - 1 - reversedIndex
        return if (neck.value == null){
            Color.Black
        } else if (index < topIndex.value){
            Color.Red
        } else if (index == topIndex.value){
            Color.Green
        } else {
            Color.Black
        }
    }

    fun getNeckColor() = if (mouth.value == null){
        Color.Black
    } else if (neck.value == null){
        Color.Green
    } else {
        Color.Red
    }

    fun getMouthColor() = if(mouth.value == null){
        Color.Green
    } else {
        Color.Red
    }

    companion object {
        const val MAX_LAYER = 8
    }
}