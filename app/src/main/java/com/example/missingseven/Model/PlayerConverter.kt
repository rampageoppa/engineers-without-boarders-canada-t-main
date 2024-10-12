package com.example.missingseven.Model

import androidx.compose.runtime.mutableStateOf
import com.example.missingseven.Database.Entity.Player

/***
 * static class to convert data base model to ui state for [Player]
 */
class PlayerConverter {

    companion object {
        fun databaseEntityToUiState(playerType: Player, countryName: String, instructions: String): PlayerUiState {
            return playerType.run {
                PlayerUiState(
                    pid,
                    cid,
                    mutableStateOf(curr_money),
                    countryName,
                    instructions,
                    mutableStateOf(neck),
                    mutableStateOf(mouth),
                    mutableStateOf(layer0),
                    mutableStateOf(layer1),
                    mutableStateOf(layer2),
                    mutableStateOf(layer3),
                    mutableStateOf(layer4),
                    mutableStateOf(layer5),
                    mutableStateOf(layer6),
                    mutableStateOf(layer7),
                    mouthRubberBanded
                )
            }
        }
    }
}