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

enum class SizeClass {
    FOURHUNDRED,
    FIVEHUNDRED,
    SIXHUNDRED,
    SEVENHUNDRED,
    EIGHTHUNDRED,
    NINEHUNDRED,
    ONETHOUSAND,
    ONEONE,
    ONETWO,
    ONETHREE,
    ONEFOUR,
    ONEFIVE,
    ONESIX,
    ONESEVEN,
    ONEEIGHT,
    ONENINE,
    TWOTHOUSAND
}

@Composable
fun Activity.rememberWindowSizeClass(): SizeClass {
    val configuration = LocalConfiguration.current
    val windowMetrics = remember(configuration) {
        WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(this)
    }
    val windowDpSize = with(LocalDensity.current) {
        windowMetrics.bounds.toComposeRect().size.toDpSize()
    }

    val widthWindowSizeClass = when {
        windowDpSize.width <= 400.dp -> SizeClass.FOURHUNDRED
        windowDpSize.width <= 500.dp -> SizeClass.FIVEHUNDRED
        windowDpSize.width <= 601.dp -> SizeClass.SIXHUNDRED
        windowDpSize.width <= 700.dp -> SizeClass.SEVENHUNDRED
        windowDpSize.width <= 800.dp -> SizeClass.EIGHTHUNDRED
        windowDpSize.width <= 900.dp -> SizeClass.NINEHUNDRED
        windowDpSize.width <= 1000.dp -> SizeClass.ONETHOUSAND
        windowDpSize.width <= 1100.dp -> SizeClass.ONEONE
        windowDpSize.width <= 1200.dp -> SizeClass.ONETWO
        windowDpSize.width <= 1300.dp -> SizeClass.ONETHREE
        windowDpSize.width <= 1400.dp -> SizeClass.ONEFOUR
        windowDpSize.width <= 1500.dp -> SizeClass.ONEFIVE
        windowDpSize.width <= 1600.dp -> SizeClass.ONESIX
        windowDpSize.width <= 1700.dp -> SizeClass.ONESEVEN
        windowDpSize.width <= 1800.dp -> SizeClass.ONEEIGHT
        windowDpSize.width <= 1900.dp -> SizeClass.ONENINE
        else -> SizeClass.TWOTHOUSAND
    }

    return widthWindowSizeClass
}