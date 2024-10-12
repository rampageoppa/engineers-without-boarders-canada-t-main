package com.example.missingseven.Database.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/***
 * data class for the table [Item]
 */
@Entity
data class Item(
    @PrimaryKey val iid: Int,
    @ColumnInfo val name: String,
    @ColumnInfo var quantity: Int,
    @ColumnInfo var price: Int,
    @ColumnInfo val strength: Float,
    @ColumnInfo val cleanedStrength: Float,
    @ColumnInfo val effectiveness: List<Float>,
    @ColumnInfo val cleanedEffectiveness: List<Float>

)