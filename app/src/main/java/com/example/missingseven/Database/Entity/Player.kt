package com.example.missingseven.Database.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/***
 * data class for the table [Player]
 */
@Entity
data class Player(
    @PrimaryKey val pid: Int,
    @ColumnInfo var cid: Int,
    @ColumnInfo var curr_money: Int,
    @ColumnInfo var neck: Int,
    @ColumnInfo var mouth: Int,
    @ColumnInfo var layer0: Int,
    @ColumnInfo var layer1: Int,
    @ColumnInfo var layer2: Int,
    @ColumnInfo var layer3: Int,
    @ColumnInfo var layer4: Int,
    @ColumnInfo var layer5: Int,
    @ColumnInfo var layer6: Int,
    @ColumnInfo var layer7: Int,
    @ColumnInfo var mouthRubberBanded: Boolean
){
    fun updatePlayerByIndex(index: Int, value: Int){
        if ( value == 6){
            if (mouth == -1){
                mouthRubberBanded = true
            }
        }
        else {
            if (mouth == -1){
                mouth = value
            } else if (neck == -1){
                neck = value
            } else {
                when (index){
                    0 -> layer0 = value
                    1 -> layer1 = value
                    2 -> layer2 = value
                    3 -> layer3 = value
                    4 -> layer4 = value
                    5 -> layer5 = value
                    6 -> layer6 = value
                    7 -> layer7 = value
                }
            }
        }
    }
}

fun reset() =
    Player(
        0,
        -1,
        0,
        -1,
        -1,
        -1,
        -1,
        -1,
        -1,
        -1,
        -1,
        -1,
        -1,
        false)

fun Player.resetFilter(money: Int) = copy(
    curr_money = money,
    neck = -1,
    mouth = -1,
    layer0 = -1,
    layer1 = -1,
    layer2 = -1,
    layer3 = -1,
    layer4 = -1,
    layer5 = -1,
    layer6 = -1,
    layer7 = -1
)
