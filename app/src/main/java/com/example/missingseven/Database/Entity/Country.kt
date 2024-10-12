package com.example.missingseven.Database.Entity

import androidx.core.content.res.ResourcesCompat.ID_NULL
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.example.missingseven.R

/***
 * data class for the table [Country]
 */
@Entity
data class Country(
    @PrimaryKey val cid: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val money: Int,
    @ColumnInfo val instruction: String,
    @ColumnInfo val priceMultiplier: Int = 1,
    @ColumnInfo val multiplierHint: String = "",
    @ColumnInfo val hasDifficultyReading: Boolean = false
)

fun Country.getImageRes() = when (cid) {
    1 -> R.drawable.canada_filter
    2 -> R.drawable.canada_first_nations_filter
    3 -> R.drawable.kuwait_filter
    4 -> R.drawable.south_africa_filter
    5 -> ID_NULL
    6 -> ID_NULL
    7 -> R.drawable.malawi_filter
    else -> ID_NULL
}
