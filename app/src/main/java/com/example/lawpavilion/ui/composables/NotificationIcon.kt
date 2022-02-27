package com.example.lawpavilion.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lawpavilion.R
import com.example.lawpavilion.ui.theme.Orange

@Composable
fun NotificationIcon(
    notificationCount: Int
) {


    Column(Modifier.padding(20.dp)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(50.dp),
                    color = Orange
                )
                .size(65.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "",
                tint = Color.White
            )
            Box(
                Modifier
                    .offset(x = -(8.dp), y = -(4.dp))
                    .align(Alignment.TopStart)
                    .background(
                        shape = RoundedCornerShape(50.dp),
                        color = Color.White.copy(alpha = 0.3f)
                    )
                    .size(32.dp)
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(
                            shape = RoundedCornerShape(50.dp),
                            color = Color.White
                        )
                        .size(24.dp)
                ){
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "$notificationCount",
                        color = Orange
                    )
                }
            }
        }
    }
}