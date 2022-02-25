package com.example.lawpavilion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.ui.unit.dp
import com.example.lawpavilion.ui.LawPavilionApp
import com.example.lawpavilion.ui.utils.WindowSizeClass
import com.example.lawpavilion.ui.utils.rememberWindowSizeClass
import com.example.lawpavilion.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get view model
        val mainActivityViewModel: MainActivityViewModel by viewModels()

        setContent {
            //retrieve the window size class
            val windowSizeClass = rememberWindowSizeClass()
            //keep track of drawer state based on window's size
            val drawerState = if (windowSizeClass ==  WindowSizeClass.COMPACTPORTRAIT) {
                rememberDrawerState(DrawerValue.Closed)
            } else {
                rememberDrawerState(DrawerValue.Open)
            }
            //keep track of rail size
            val railSizeIfSmallScreen = if (windowSizeClass == WindowSizeClass.COMPACTPORTRAIT ||
                windowSizeClass == WindowSizeClass.MEDIUMPORTRAIT ) {
                76.dp
            } else {
                230.dp
            }

            val railSizeIfLargeScreen = if (windowSizeClass !== WindowSizeClass.COMPACTPORTRAIT ||
                windowSizeClass !== WindowSizeClass.MEDIUMPORTRAIT ) {
                230.dp
            } else {
                76.dp
            }

            //compose app, pass window size class and class activity
            LawPavilionApp(windowSizeClass, drawerState, railSizeIfSmallScreen, railSizeIfLargeScreen, mainActivityViewModel)
        }
    }
}
