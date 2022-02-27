package com.example.lawpavilion.ui.utils

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

fun customShape(left: Float, right: Float = Float.MAX_VALUE, bottom: Float = Float.MAX_VALUE) =  object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(Rect(left,0f,right, bottom))
    }

}