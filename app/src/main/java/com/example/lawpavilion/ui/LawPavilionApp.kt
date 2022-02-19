package com.example.lawpavilion.ui

import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import com.example.lawpavilion.ui.theme.LawPavilionTheme
import com.example.lawpavilion.ui.utils.WindowSizeClass

@Composable
fun LawPavilionApp(windowSize: WindowSizeClass) {
    LawPavilionTheme {
        val drawerState = rememberDrawerState(DrawerValue.Closed)

    }
}