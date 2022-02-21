package com.example.lawpavilion.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lawpavilion.R

private val WorkSans = FontFamily(
    Font(R.font.work_sans)
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = WorkSans,

    h2 = TextStyle (
        fontSize = 16.sp,
        fontWeight = FontWeight.ExtraBold
            ),
    h3 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    ),
    h5 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    h4 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 15.sp
    )

)

