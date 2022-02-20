package com.example.lawpavilion.ui.utils


import android.app.Activity
import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowMetricsCalculator

enum class WindowSizeClass { COMPACT, MEDIUM, EXPANDED }

@Composable
fun Activity.rememberWindowSizeClass(): WindowSizeClass {
    val configuration = LocalConfiguration.current
    val windowMetrics = remember(configuration) {
        WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(this)
    }
    val windowDpSize = with(LocalDensity.current) {
        windowMetrics.bounds.toComposeRect().size.toDpSize()
    }

    val widthWindowSizeClass = when {
        windowDpSize.width < 1000.dp -> WindowSizeClass.COMPACT
        windowDpSize.width < 1400.dp -> WindowSizeClass.MEDIUM
        else -> WindowSizeClass.EXPANDED
    }
    return widthWindowSizeClass
//    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//        val widthWindowSizeClass = when {
//            windowDpSize.width < 600.dp -> WindowSizeClass.EXPANDED
//            windowDpSize.width < 840.dp -> WindowSizeClass.MEDIUM
//            else -> WindowSizeClass.COMPACT
//        }
//        return widthWindowSizeClass
//    } else {
//        val widthWindowSizeClass = when {
//            windowDpSize.width < 600.dp -> WindowSizeClass.COMPACT
//            windowDpSize.width < 840.dp -> WindowSizeClass.MEDIUM
//            else -> WindowSizeClass.EXPANDED
//        }
//        return widthWindowSizeClass
//    }

}