package com.example.lawpavilion.ui.utils


import android.app.Activity
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator

enum class WindowSizeClass {
    COMPACTLAND,
    COMPACTPORTRAIT,
    MEDIUMLAND,
    MEDIUMPORTRAIT,
    EXPANDEDLAND,
    EXPANDEDPORTRAIT,
    EXTRALAND,
    EXTRAPORTRAIT
}

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

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        val widthWindowSizeClass = when {
            windowDpSize.width < 600.dp -> WindowSizeClass.COMPACTLAND
            windowDpSize.width < 840.dp -> WindowSizeClass.MEDIUMLAND
            windowDpSize.width < 1300.dp -> WindowSizeClass.EXPANDEDLAND
            else -> WindowSizeClass.EXTRALAND
        }
        return widthWindowSizeClass
    } else {
        val widthWindowSizeClass = when {
            windowDpSize.width < 600.dp -> WindowSizeClass.COMPACTPORTRAIT
            windowDpSize.width < 840.dp -> WindowSizeClass.MEDIUMPORTRAIT
            windowDpSize.width < 960.dp -> WindowSizeClass.EXPANDEDPORTRAIT
            else -> WindowSizeClass.EXTRAPORTRAIT
        }
        return widthWindowSizeClass
    }

}