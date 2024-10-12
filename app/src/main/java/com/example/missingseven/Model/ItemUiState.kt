package com.example.missingseven.Model

import com.example.missingseven.Database.Entity.Item

/***
 * data class of the ui model for [Item]
 */
data class ItemUiState(
    val iid: Int,
    val name: String,
    var quantity: Int,
    var price: Int,
    var img: Int,
    val strength: Float,
    val cleanedStrength: Float,
    val effectiveness: List<Float>,
    val cleanedEffectiveness: List<Float>
)

fun ItemUiState.getMultipliedPrice(multiplier: Int) = price * multiplier