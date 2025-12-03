package com.api.pokemones.presentation.ui.components.colorsstats

import androidx.compose.ui.graphics.Color

/**
 * @Author: Alejandro Ambrosio
 * @Date: 01/07/25
 * @Description:
 */

fun getStatColor(statName: String): Color {
    return when (statName.lowercase()) {
        "hp" -> Color(0xFF4CAF50)
        "attack", "atq. esp" -> Color(0xFFF44336)
        "defense", "def. esp" -> Color(0xFF2196F3)
        else -> Color(0xFFFFC107)
    }
}