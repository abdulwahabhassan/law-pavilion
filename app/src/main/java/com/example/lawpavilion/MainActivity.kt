package com.example.lawpavilion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.lawpavilion.ui.LawPavilionApp
import com.example.lawpavilion.ui.theme.LawPavilionTheme
import com.example.lawpavilion.ui.utils.WindowSizeClass
import com.example.lawpavilion.ui.utils.rememberWindowSizeClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //retrieve the window size class
            val windowSizeClass = rememberWindowSizeClass()
            LawPavilionApp(windowSizeClass)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    LawPavilionApp()
}