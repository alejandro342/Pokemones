package com.api.pokemones.presentation.screen.viewcontent.detailview.components

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

/**
 * @Author: Alejandro Ambrosio
 * @Date: 06/11/25
 * @modificationDate: 06/11/25
 * @Description:
 */
class BottomArcShape(private val arcHeight: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        val width = size.width
        val height = size.height

        val path = Path().apply {

            moveTo(0f, 0f)
            lineTo(0f, height - arcHeight)
            quadraticTo(   width / 2, height + arcHeight,
                width, height - arcHeight
            )
            lineTo(width, 0f)
            close()
        }

        return Outline.Generic(path)
    }
}