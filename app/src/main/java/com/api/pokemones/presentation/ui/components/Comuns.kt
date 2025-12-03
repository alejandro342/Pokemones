package com.api.pokemones.presentation.ui.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * @Author: Alejandro Ambrosio
 * @Date: 05/11/25
 * @modificationDate: 05/11/25
 * @Description: elementos comunes reutilizables
 */

 /*fun getColorForType(type: String): Color {
    return when (type.lowercase()) {
        "fire" -> Color(0xFFFFA726)
        "water" -> Color(0xFF42A5F5)
        "electric" -> Color(0xFFFFEE58)
        "grass" -> Color(0xFF66BB6A)
        "poison" -> Color(0xFFAB47BC)
        "flying" -> Color(0xFFB39DDB)
        "bug" -> Color(0xFFC0CA33)
        else -> Color(0xFF9E9E9E)
    }
}*/

 fun getMetallicBrushForType(type: String): Brush {
    return when (type.lowercase()) {
        "fire" -> Brush.linearGradient(
            colors = listOf(Color(0xFFFFA726), Color(0xFFFFCC80), Color(0xFFFFA726)),
            start = Offset.Zero,
            end = Offset(1000f, 1000f)
        )

        "water" -> Brush.linearGradient(
            colors = listOf(Color(0xFF42A5F5), Color(0xFF90CAF9), Color(0xFF42A5F5)),
            start = Offset.Zero,
            end = Offset(1000f, 1000f)
        )

        "electric" -> Brush.linearGradient(
            colors = listOf(Color(0xFFFFEE58), Color(0xFFFFF176), Color(0xFFFFEE58)),
            start = Offset.Zero,
            end = Offset(1000f, 1000f)
        )

        "grass" -> Brush.linearGradient(
            colors = listOf(Color(0xFF66BB90), Color(0xFFA5D6B8), Color(0xFF66BB6A)),
            start = Offset.Zero,
            end = Offset(1000f, 1000f)
        )

        "poison" -> Brush.linearGradient(
            colors = listOf(Color(0xFFAB47BC), Color(0xFFCE93D8), Color(0xFFAB47BC)),
            start = Offset.Zero,
            end = Offset(1000f, 1000f)
        )

        "flying" -> Brush.linearGradient(
            colors = listOf(Color(0xFFB39DDB), Color(0xFFD1C4E9), Color(0xFFB39DDB)),
            start = Offset.Zero,
            end = Offset(1000f, 1000f)
        )

        "bug" -> Brush.linearGradient(
            colors = listOf(Color(0xFFC0CA33), Color(0xFFDCE775), Color(0xFFC0CA33)),
            start = Offset.Zero,
            end = Offset(1000f, 1000f)
        )

        else -> Brush.linearGradient(
            colors = listOf(Color(0xFF9E9E9E), Color(0xFFE0E0E0), Color(0xFF9E9E9E)),
            start = Offset.Zero,
            end = Offset(1000f, 1000f)
        )
    }
}